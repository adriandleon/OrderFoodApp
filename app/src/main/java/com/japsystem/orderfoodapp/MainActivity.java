package com.japsystem.orderfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignUp)
    public void onSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @OnClick(R.id.btnSignIn)
    public void onSignIn() {
        startActivity(new Intent(this, SignInActivity.class));
    }
}
