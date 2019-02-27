package com.the.bamstroyputs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.util.ActivityUtil;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActivityUtil.pushFragment(GeneralFragment.newInstance(), getSupportFragmentManager(), R.id.container, false);
    }
}
