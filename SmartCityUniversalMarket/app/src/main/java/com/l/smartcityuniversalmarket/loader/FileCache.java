package com.l.smartcityuniversalmarket.loader;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileCache {
    File fileCache;

    public FileCache(Context context, String cacheName) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            fileCache = new File(Environment.getExternalStorageDirectory(), cacheName);
        else
            fileCache = context.getCacheDir();
        if (!fileCache.exists())
            fileCache.mkdirs();
    }

    public File getFile(String url) {
        return new File(fileCache, String.valueOf(url.hashCode()));
    }

    public void clear() {
        File[] files = fileCache.listFiles();
        if (!(files == null))
            for (File file : files)
                file.delete();
    }

}
