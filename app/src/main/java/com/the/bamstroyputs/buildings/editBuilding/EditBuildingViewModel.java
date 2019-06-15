package com.the.bamstroyputs.buildings.editBuilding;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.Building;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBuildingViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    private BamsService service;

    public EditBuildingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getMutableLiveData() {
        return mutableLiveData;
    }

    public void changeBuildingData(String numberFloors, String projectId, String buildingId, String name){
        if(name.length() == 0 || numberFloors.length() == 0){
            Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        service = BamsClient.getClient().create(BamsService.class);
        service.changeBuildingNameFloorNumbers(DataController.getInstance().getUser().getToken(), numberFloors, projectId, buildingId, name).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    mutableLiveData.setValue(true);
                }else {
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
