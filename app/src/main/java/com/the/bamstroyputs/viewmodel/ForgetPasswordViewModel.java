package com.the.bamstroyputs.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordViewModel extends AndroidViewModel {
    private MutableLiveData<String> emailMutableData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isOk = new MutableLiveData<>();
    private BamsService service;

    public ForgetPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getEmailMutableData() {
        return emailMutableData;
    }

    public MutableLiveData<Boolean> getIsOk() {
        return isOk;
    }

    public void sendEmailRequest(String email){
        service = BamsClient.getClient().create(BamsService.class);
        service.sendEmail(email).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    isOk.setValue(true);
                }else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.wrong_email), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}
