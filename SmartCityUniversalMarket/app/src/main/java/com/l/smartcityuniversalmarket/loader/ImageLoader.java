package com.l.smartcityuniversalmarket.loader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.l.smartcityuniversalmarket.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
     MemoryCache memoryCache = new MemoryCache();
     //final int onLoadImageId = R.drawable.food;
     Handler handler = new Handler();
     ExecutorService executorService;
     FileCache fileCache;

    public ImageLoader(Context context) {
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }
        int stubId = R.drawable.food;
    public void displayImage(String url, int loader, ImageView imageView) {
        stubId = loader;
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            RequiredPhoto requiredPhoto = new RequiredPhoto(url, imageView);
            executorService.submit(new RequiredPhotoLoader(requiredPhoto));
            imageView.setImageResource(loader);
        }
    }

    public Bitmap getBitmap(String url) {
        File file = fileCache.getFile(url);
        Bitmap bitmap = decodeFile(file);
        if (bitmap != null)
            return bitmap;
        try {
            URL urlToImage = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlToImage.openConnection();
            connection.setConnectTimeout(100000);
            connection.setReadTimeout(100000);
            connection.setInstanceFollowRedirects(true);
            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            Utill.copyStream(inputStream, outputStream);
            outputStream.close();
            connection.disconnect();
            return decodeFile(file);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Bitmap decodeFile(File file) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            final int PREDEFINED_SIZE = 70;
            int width = options.outWidth,
                    height = options.outHeight,
                    scale = 1;
            for (; ; ) {
                if (width / 2 < PREDEFINED_SIZE || height / 2 < PREDEFINED_SIZE)
                    break;
                width /= 2;
                height /= 2;
                scale *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = scale;
            fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream, null, options2);
            fileInputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isImageViewReused(RequiredPhoto requiredPhoto) {
        String tag = imageViews.get(requiredPhoto.imageView);
        return (tag == null || !tag.equals(requiredPhoto.url));
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

    private class RequiredPhoto {
        String url;
        ImageView imageView;

        RequiredPhoto(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }
    }

    private class RequiredPhotoLoader implements Runnable {
        RequiredPhoto requiredPhoto;

        RequiredPhotoLoader(RequiredPhoto requiredPhoto) {
            this.requiredPhoto = requiredPhoto;
        }

        @Override
        public void run() {
            try {
                if (isImageViewReused(requiredPhoto))
                    return;
                Bitmap bitmap = getBitmap(requiredPhoto.url);
                memoryCache.put(requiredPhoto.url, bitmap);
                if (isImageViewReused(requiredPhoto))
                    return;
                BitmapDisplayer bitmapDisplayer = new BitmapDisplayer(bitmap, requiredPhoto);
                handler.post(bitmapDisplayer);
                //new Handler(Looper.getMainLooper()).post(bitmapDisplayer);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        RequiredPhoto requiredPhoto;

        BitmapDisplayer(Bitmap bitmap, RequiredPhoto requiredPhoto) {
            this.bitmap = bitmap;
            this.requiredPhoto = requiredPhoto;
        }

        @Override
        public void run() {
            if (isImageViewReused(requiredPhoto))
                return;
            if (bitmap != null)
                requiredPhoto.imageView.setImageBitmap(bitmap);
            else
                requiredPhoto.imageView.setImageResource(stubId);
        }
    }


}
