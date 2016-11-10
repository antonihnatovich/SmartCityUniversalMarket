package com.l.smartcityuniversalmarket.loader;

import android.graphics.Bitmap;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryCache {
    private static final String MEMORY_TAG = "MemoryCache";
    private Map<String, Bitmap> memoryCache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(20, 1.5f, true));
    private long size = 0,
            limit = 64 * 1024 * 1024;

    public MemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long limit) {
        this.limit = limit;
    }

    public Bitmap get(String id) {
        try {
            if (memoryCache.containsKey(id))
                return memoryCache.get(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void put(String id, Bitmap bitmap) {
        try {
            if (memoryCache.containsKey(id))
                size -= getSizeInBytes(memoryCache.get(id));
            memoryCache.put(id, bitmap);
            size += getSizeInBytes(bitmap);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void checkSize() {
        if (size > limit) {
            Iterator<Map.Entry<String, Bitmap>> iterator = memoryCache.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Bitmap> entry = iterator.next();
                size -= getSizeInBytes(entry.getValue());
                iterator.remove();
                if (size == limit)
                    break;
            }
        }
    }

    public void clear() {
        try {
            memoryCache.clear();
            size = 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getRowBytes() * bitmap.getHeight();
    }
}
