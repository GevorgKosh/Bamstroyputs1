package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.the.bamstroyputs.BR;

public class User extends BaseObservable {
    private String name;
    private String email;
    private String password;
    private String token;
    private String company;
    private String phone_number;

    public User() {
    }

    public User(String name, String email, String password, String token, String company, String phone_number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.company = company;
        this.phone_number = phone_number;
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
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
        notifyPropertyChanged(BR.company);
    }

    @Bindable
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
        notifyPropertyChanged(BR.phone_number);
    }
}
