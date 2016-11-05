package com.l.smartcityuniversalmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.am_button1).setOnClickListener(this);
        findViewById(R.id.am_button2).setOnClickListener(this);
        findViewById(R.id.am_button3).setOnClickListener(this);
        findViewById(R.id.am_button4).setOnClickListener(this);
        findViewById(R.id.am_button5).setOnClickListener(this);
        findViewById(R.id.am_button6).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.am_button1: {
                startActivity(new Intent(this, ProductActivity.class));
                break;
            }
            case R.id.am_button2: {
                startActivity(new Intent(this, BasketActivity.class));
                break;
            }
            case R.id.am_button3: {
                startActivity(new Intent(this, AccountActivity.class));
                break;
            }
            case R.id.am_button4: {
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            }
        }
    }


}
