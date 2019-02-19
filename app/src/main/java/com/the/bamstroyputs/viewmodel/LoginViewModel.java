package com.the.bamstroyputs.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.the.bamstroyputs.HomeActivity;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.User;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;
import com.the.bamstroyputs.util.ActivityUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private BamsService service;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void loginRequest(String email, String password){
        service = BamsClient.getClient().create(BamsService.class);
        service.logIn(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    userMutableLiveData.setValue(user);
                    Log.d("RESPONSE", response.toString());
                }else {
                    Log.d("RESPONSE", "RES " + response.toString() + " NO SUCCESS");

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("RESPONSE", t.getMessage() + "FAILURE");

            }
        });
    }
}
