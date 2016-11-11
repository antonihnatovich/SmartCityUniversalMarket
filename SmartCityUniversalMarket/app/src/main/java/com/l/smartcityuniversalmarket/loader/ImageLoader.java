package com.l.smartcityuniversalmarket.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.l.smartcityuniversalmarket.R;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    private MemoryCache memoryCache = new MemoryCache();
    private final int onLoadImageId = R.drawable.food;
    private Handler handler = new Handler();
    private ExecutorService executorService;
    private FileCache fileCache;

    public ImageLoader(Context context) {
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(4);
    }

    public void displayImage(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            RequiredPhoto requiredPhoto = new RequiredPhoto(url, imageView);
            executorService.submit(new RequiredPhotoLoader(requiredPhoto));
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            return null;
        }
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
                Bitmap bitmap = getBitmapFromURL(requiredPhoto.url);
                memoryCache.put(requiredPhoto.url, bitmap);
                if (isImageViewReused(requiredPhoto))
                    return;
                BitmapDisplayer bitmapDisplayer = new BitmapDisplayer(bitmap, requiredPhoto);
                handler.post(bitmapDisplayer);
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
                requiredPhoto.imageView.setImageResource(onLoadImageId);
        }
    }

    private boolean isImageViewReused(RequiredPhoto requiredPhoto) {
        String tag = imageViews.get(requiredPhoto.imageView);
        return (tag == null || !tag.equals(requiredPhoto.url));
    }

}
