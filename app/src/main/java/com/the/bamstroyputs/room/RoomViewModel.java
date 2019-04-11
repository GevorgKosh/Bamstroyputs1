package com.the.bamstroyputs.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.model.Room;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomViewModel extends AndroidViewModel {
    private MutableLiveData<List<Room>> roomListMutableLiveData;
    private MutableLiveData<Room> addRoomMutableLiveData;
    private BamsService service = BamsClient.getClient().create(BamsService.class);

    public RoomViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Room>> getRoomListMutableLiveData(String floorId) {
        if (roomListMutableLiveData == null) {
            roomListMutableLiveData = new MutableLiveData<>();
        }
        getRoomsFromServer(floorId);

        return roomListMutableLiveData;
    }

    public MutableLiveData<Room> getAddRoomMutableLiveData() {
        if (addRoomMutableLiveData == null) {
            addRoomMutableLiveData = new MutableLiveData<>();
        }

        return addRoomMutableLiveData;
    }

    public void getRoomsFromServer(String floorId) {
        service.getRooms(DataController.getInstance().getUser().getToken(), floorId, "1", "100000").enqueue(new Callback<ResponseModel<List<Room>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Room>>> call, Response<ResponseModel<List<Room>>> response) {
                if (response.isSuccessful()) {
                    roomListMutableLiveData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Room>>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addRoom(String username, String roomNumber, String floorId) {
        service.createRoom(DataController.getInstance().getUser().getToken(), username, roomNumber, floorId).enqueue(new Callback<ResponseModel<Room>>() {
            @Override
            public void onResponse(Call<ResponseModel<Room>> call, Response<ResponseModel<Room>> response) {
                if (response.isSuccessful()) {
                    List<Room> rooms = roomListMutableLiveData.getValue();
                    rooms.add(response.body().getData());
                    roomListMutableLiveData.setValue(rooms);
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.successful_new_room), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<Room>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
