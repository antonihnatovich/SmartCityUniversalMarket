package com.l.smartcityuniversalmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findViewById(R.id.ra_login_button).setOnClickListener(this);
        findViewById(R.id.ra_ready_register_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ra_ready_register_button: {
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case R.id.ra_login_button: {
                startActivity(new Intent(this, LoginActivity.class));
                break;
            }
        }
    }

}
