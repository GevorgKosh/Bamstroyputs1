package com.the.bamstroyputs.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.the.bamstroyputs.model.User;
import com.the.bamstroyputs.util.Constants;
import com.the.bamstroyputs.util.PreferenceUtil;

public class DataController {
    private static final DataController ourInstance = new DataController();
    private User user;

    public static DataController getInstance() {
        return ourInstance;
    }

    private DataController() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSignIn(){
        return user != null;
    }

    public void init(Context context) {
        String userString = PreferenceUtil.getString(context, Constants.USER_GLOBAL,null);
        Gson gson = new Gson();
        if (userString != null){
            user = gson.fromJson(userString,User.class);
        }

    }
}
