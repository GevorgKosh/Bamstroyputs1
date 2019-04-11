package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.bamstroyputs.BR;

public class Building extends BaseObservable {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("naumber_of_foolrs")
    private String numberFloors;

    @Expose
    @SerializedName("name")
    private String name;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getNumberFloors() {
        return numberFloors;
    }

    public void setNumberFloors(String numberFloors) {
        this.numberFloors = numberFloors;
        notifyPropertyChanged(BR.numberFloors);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
