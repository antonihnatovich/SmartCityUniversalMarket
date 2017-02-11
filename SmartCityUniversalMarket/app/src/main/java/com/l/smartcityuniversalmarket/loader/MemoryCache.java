package com.l.smartcityuniversalmarket.loader;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryCache {
    private static final String MEMORY_TAG = "MemoryCache";
    private Map<String, SoftReference<Bitmap>> memoryCache = Collections.synchronizedMap(new LinkedHashMap<String, SoftReference<Bitmap>>());
    private long size = 0,
            limit = 64 * 1024 * 1024;

    public MemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long limit) {
        this.limit = limit;
    }

    public Bitmap get(String id) {
        if(!memoryCache.containsKey(id))
            return null;
        SoftReference<Bitmap> bitmapSoftReference = memoryCache.get(id);
        return bitmapSoftReference.get();
    }

    public void put(String id, Bitmap bitmap) {
        memoryCache.put(id, new SoftReference<Bitmap>(bitmap));
    }

//    private void checkSize() {
//        if (size > limit) {
//            Iterator<Map.Entry<String, SoftReference<Bitmap>>> iterator = memoryCache.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, Bitmap> entry = iterator.next();
//                size -= getSizeInBytes(entry.getValue());
//                iterator.remove();
//                if (size == limit)
//                    break;
//            }
//        }
//    }

    public void clear() {
        memoryCache.clear();
    }

    private long getSizeInBytes(Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getRowBytes() * bitmap.getHeight();
    }
}
