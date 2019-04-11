package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.the.bamstroyputs.BR;

public class User extends BaseObservable {
    private String id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String active;
    private String token;
    private String phone_number;
    private String type;

    public User() {
    }

    public User(String username, String name, String email, String password, String active, String phone_number) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.phone_number = phone_number;
    }

    public User(String username, String name, String email, String password, String active, String token, String phone_number, String type) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.token = token;
        this.phone_number = phone_number;
        this.type = type;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
        notifyPropertyChanged(BR.active);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        notifyPropertyChanged(BR.token);
    }

    @Bindable
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        notifyPropertyChanged(BR.phone_number);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active='" + active + '\'' +
                ", token='" + token + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
