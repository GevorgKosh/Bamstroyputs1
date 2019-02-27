package com.the.bamstroyputs.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.the.bamstroyputs.model.GeneralItem;

public class GeneralViewModel extends AndroidViewModel {
    private MutableLiveData<GeneralItem> itemMutableLiveData = new MutableLiveData<>();

    public GeneralViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<GeneralItem> getItemMutableLiveData() {
        return itemMutableLiveData;
    }


}
