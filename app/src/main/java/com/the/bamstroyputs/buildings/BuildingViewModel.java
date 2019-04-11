package com.the.bamstroyputs.buildings;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingViewModel extends AndroidViewModel {
    private MutableLiveData<List<Building>> listMutableLiveData;
    private MutableLiveData<Building> buildingMutableLiveData;
    private BamsService service = BamsClient.getClient().create(BamsService.class);

    public BuildingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Building>> getListMutableLiveData(String project_id) {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
        }
        getBuildingsFromServer(project_id);

        return listMutableLiveData;
    }

    public MutableLiveData<Building> getBuildingMutableLiveData() {
        if(buildingMutableLiveData == null){
            buildingMutableLiveData = new MutableLiveData<>();
        }

        return buildingMutableLiveData;
    }

    public void getBuildingsFromServer(String project_id) {
        service.getBuildings(DataController.getInstance().getUser().getToken(), "1", "100000", project_id).enqueue(new Callback<ResponseModel<List<Building>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Building>>> call, Response<ResponseModel<List<Building>>> response) {
                if(response.isSuccessful()){
                    listMutableLiveData.setValue(response.body().getData());
                }else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Building>>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createBuilding(String name, String numberFloors, String project_id){
        service.createBuilding(DataController.getInstance().getUser().getToken(), name, numberFloors, project_id).enqueue(new Callback<ResponseModel<Building>>() {
            @Override
            public void onResponse(Call<ResponseModel<Building>> call, Response<ResponseModel<Building>> response) {
                if(response.isSuccessful()){
                    getBuildingMutableLiveData().setValue(response.body().getData());
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.successful_new_building), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<Building>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
