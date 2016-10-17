package com.l.smartcityuniversalmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.l.smartcityuniversalmarket.data.api.ui.MainPresenter;

public class BackendTestActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_test);
        textView = (TextView)findViewById(R.id.responseView);
        textView.setText(BuildConfig.LOL);
    }
}
