package com.the.bamstroyputs.authentication.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.model.User;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;
import com.the.bamstroyputs.util.Constants;
import com.the.bamstroyputs.util.PreferenceUtil;
import com.the.bamstroyputs.util.Utils;

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
        if (!Utils.allowToLogin(getApplication(), email, password,
                getApplication().getResources().getString(R.string.empty_fields))) {
            return;
        }
        if (!Utils.isValidEmail(email)){
            Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.wrong_email_format), Toast.LENGTH_SHORT).show();
            return;
        }
        service = BamsClient.getClient().create(BamsService.class);
        service.logIn(email, password).enqueue(new Callback<ResponseModel<User>>() {
            @Override
            public void onResponse(Call<ResponseModel<User>> call, Response<ResponseModel<User>> response) {
                if(response.isSuccessful()){
                    User user = response.body().getData();
                    DataController.getInstance().setUser(user);
                    Gson gson = new Gson();
                    PreferenceUtil.putString(getApplication(), Constants.USER_GLOBAL, gson.toJson(user));
                    userMutableLiveData.setValue(user);
                }else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.wrong_email_pass), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<User>> call, Throwable t) {
                Log.d("RESPONSE", t.getMessage() + "FAILURE");
            }
        });
    }
}
