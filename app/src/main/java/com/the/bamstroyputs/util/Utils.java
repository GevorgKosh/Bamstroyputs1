package com.the.bamstroyputs.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class Utils {
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean allowToLogin(Context context, String email, String password, String message){
        boolean isAllow = true;
        if(email.length() == 0 ||  password.length() == 0){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            isAllow = false;
            return isAllow;
        }
        if(password.length() < 5){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            isAllow = false;
            return isAllow;
        }
        return isAllow;
    }

    public static boolean allowToLogin(Context context, String email, String message){
        boolean isAllow = true;
        if(email.length() == 0){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            isAllow = false;
            return isAllow;
        }
        return isAllow;
    }
}
