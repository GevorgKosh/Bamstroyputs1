package com.the.bamstroyputs.project;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.Project;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectViewModel extends AndroidViewModel {
    private MutableLiveData<List<Project>> itemMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Project> addItemMutableLiveData;
    private BamsService service = BamsClient.getClient().create(BamsService.class);


    public ProjectViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Project>> getItemMutableLiveData() {
        getProjects();
        return itemMutableLiveData;
    }

    public MutableLiveData<Project> getAddItemMutableLiveData() {
        if (addItemMutableLiveData == null) {
            addItemMutableLiveData = new MutableLiveData<>();
        }
        return addItemMutableLiveData;
    }

    public void getProjects() {
        service.getProjects(DataController.getInstance().getUser().getToken(), "1", "100000").enqueue(new Callback<ResponseModel<List<Project>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Project>>> call, Response<ResponseModel<List<Project>>> response) {
                if (response.isSuccessful()) {
                    List<Project> projects = response.body().getData();
                    itemMutableLiveData.setValue(projects);
                } else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Project>>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage().toLowerCase(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addItem(String name) {
        service.createProject(DataController.getInstance().getUser().getToken(), name).enqueue(new Callback<ResponseModel<Project>>() {
            @Override
            public void onResponse(Call<ResponseModel<Project>> call, Response<ResponseModel<Project>> response) {
                if (response.isSuccessful()) {
                    getAddItemMutableLiveData().setValue(response.body().getData());
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.successful_new_project), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel<Project>> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
