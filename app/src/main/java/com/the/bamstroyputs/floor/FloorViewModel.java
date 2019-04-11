package com.the.bamstroyputs.floor;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.Floor;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FloorViewModel extends AndroidViewModel {
    private MutableLiveData<List<Floor>> floorListMutableLiveData;
    private MutableLiveData<Floor> floorMutableLiveData;
    private BamsService service = BamsClient.getClient().create(BamsService.class);

    public FloorViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Floor>> getFloorListMutableLiveData(String building_id) {
        if (floorListMutableLiveData == null) {
            floorListMutableLiveData = new MutableLiveData<>();
        }
        getFloorsFromServer(building_id);

        return floorListMutableLiveData;
    }

    public MutableLiveData<Floor> getFloorMutableLiveData() {
        if (floorMutableLiveData == null) {
            floorMutableLiveData = new MutableLiveData<>();
        }

        return floorMutableLiveData;
    }

    public void getFloorsFromServer(String building_id) {
        service.getFloors(DataController.getInstance().getUser().getToken(), building_id, "1", "100000").enqueue(new Callback<ResponseModel<List<Floor>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Floor>>> call, Response<ResponseModel<List<Floor>>> response) {
                if (response.isSuccessful()) {
                    floorListMutableLiveData.setValue(response.body().getData());
                } else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Floor>>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addFloor(final String building_id) {
//        int position = 0;
//        if (getFloorListMutableLiveData(building_id) != null) {
//            position = getFloorListMutableLiveData(building_id).getValue().size() - 1;
//        }  TODO
        String name = String.format(getApplication().getResources().getString(R.string.concret_floor), 2);
        service.createFloor(DataController.getInstance().getUser().getToken(), name, building_id).enqueue(new Callback<ResponseModel<Floor>>() {
            @Override
            public void onResponse(Call<ResponseModel<Floor>> call, Response<ResponseModel<Floor>> response) {
                if (response.isSuccessful()) {
                    getFloorMutableLiveData().setValue(response.body().getData());
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.successful_new_floor), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong) + response.message() + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("RESPNSE", response.message() + " " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<Floor>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
