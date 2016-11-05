package com.l.smartcityuniversalmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.l.smartcityuniversalmarket.loader.ImageLoader;

public class EmptyActivity extends AppCompatActivity {
TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        view = (TextView)findViewById(R.id.tw);
        try {
            view.setText(ImageLoader.getBitmapFromURL("https://pp.vk.me/c626627/v626627165/43a24/uk_OEzrWGms.jpg").toString());
        } catch (NullPointerException e){
            Log.d("kek", e.toString());
        }
    }
}
