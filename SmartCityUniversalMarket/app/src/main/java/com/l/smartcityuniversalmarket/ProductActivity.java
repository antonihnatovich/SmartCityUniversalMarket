package com.l.smartcityuniversalmarket;

import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        List<AlphabeticIndex.Record> recordList = new ArrayList<AlphabeticIndex.Record>();
        
    }
}
