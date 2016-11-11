package com.l.smartcityuniversalmarket.loader;

import java.io.InputStream;
import java.io.OutputStream;

public class Utill {
    private static final int BUFFER_SIZE_MAX = 1024;

    public static void copyStream(InputStream inputStream, OutputStream outputStream) {
        int count;
        try {
            byte buffer[] = new byte[BUFFER_SIZE_MAX];
            while (true) {
                count = inputStream.read(buffer, 0, BUFFER_SIZE_MAX);
                if (count == -1) break;
                outputStream.write(buffer, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
