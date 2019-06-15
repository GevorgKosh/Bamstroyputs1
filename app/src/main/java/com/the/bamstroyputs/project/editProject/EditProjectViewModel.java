package com.the.bamstroyputs.project.editProject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.model.ProjectEditResponse;
import com.the.bamstroyputs.model.ResponseModel;
import com.the.bamstroyputs.networking.BamsClient;
import com.the.bamstroyputs.networking.BamsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProjectViewModel extends AndroidViewModel {
    private MutableLiveData<String> mutableLiveData;
    private BamsService service;

    public EditProjectViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getMutableLiveData(String name, String project_id) {
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
        changeProjectName(name, project_id);

        return mutableLiveData;
    }

    public void changeProjectName(String name, String project_id){
        if(name.equals("") || name.length() == 0){
            Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            return;
        }
        service = BamsClient.getClient().create(BamsService.class);
        service.changeProjectName(DataController.getInstance().getUser().getToken(), name, project_id).enqueue(new Callback<ResponseModel<ProjectEditResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<ProjectEditResponse>> call, Response<ResponseModel<ProjectEditResponse>> response) {
                if(response.isSuccessful()){
                    mutableLiveData.setValue(response.body().getData().getName());
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.project_name_successfull_change), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ProjectEditResponse>> call, Throwable t) {
                Toast.makeText(getApplication(), getApplication().getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
