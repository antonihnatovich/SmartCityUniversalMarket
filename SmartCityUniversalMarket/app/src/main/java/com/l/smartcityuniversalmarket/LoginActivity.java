package com.l.smartcityuniversalmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.la_register_button).setOnClickListener(this);
        findViewById(R.id.la_apply_button).setOnClickListener(this);
        findViewById(R.id.la_forgot_pass_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.la_apply_button: {
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case R.id.la_forgot_pass_button: {
                break;
            }
            case R.id.la_register_button: {
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            }

        }
    }
}
