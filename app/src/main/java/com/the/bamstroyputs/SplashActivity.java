package com.the.bamstroyputs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.util.ActivityUtil;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // DataController.getInstance().init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // startAuthenticationActivity();
                startActivity(new Intent(SplashActivity.this, AuthenticationActivity.class));
            }
        },SPLASH_TIME);
    }
//TODO verevinneri het
//    private void startAuthenticationActivity(){
//        if (DataController.getInstance().isSignIn()){
//            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//
//        }else {
//            startActivity(new Intent(SplashActivity.this, AuthenticationActivity.class));
//        }
//        finish();
//    }
}
