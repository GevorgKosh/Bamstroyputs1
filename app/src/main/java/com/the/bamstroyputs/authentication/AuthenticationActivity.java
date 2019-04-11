package com.the.bamstroyputs.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.authentication.login.LoginFragment;
import com.the.bamstroyputs.util.ActivityUtil;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        ActivityUtil.pushFragment(LoginFragment.newInstance(), getSupportFragmentManager(), R.id.fragment, false);
    }
}
