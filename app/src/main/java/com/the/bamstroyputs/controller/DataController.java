package com.the.bamstroyputs.controller;

import com.the.bamstroyputs.model.User;

public class DataController {
    private static final DataController ourInstance = new DataController();
    private User user = new User();

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
}
