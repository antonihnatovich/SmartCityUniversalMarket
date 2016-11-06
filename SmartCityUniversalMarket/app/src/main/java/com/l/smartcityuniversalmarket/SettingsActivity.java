package com.l.smartcityuniversalmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner spinnerSize = (Spinner) findViewById(R.id.sa_spinner_size);
        Spinner spinnerFont = (Spinner) findViewById(R.id.sa_spinner_font);
        findViewById(R.id.sa_button_apply).setOnClickListener(this);
        findViewById(R.id.sa_button_cancel).setOnClickListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.size));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.font));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFont.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sa_button_apply:{
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case R.id.sa_button_cancel:{
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
        }
    }
}
