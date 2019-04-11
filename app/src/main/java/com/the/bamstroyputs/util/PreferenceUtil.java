package com.the.bamstroyputs.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    public static void putString(Context context, String key, String value){
        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCE_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defValue);
    }
}
