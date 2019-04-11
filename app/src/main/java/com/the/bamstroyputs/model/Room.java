package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.bamstroyputs.BR;

public class Room extends BaseObservable {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("room_number")
    private String roomNumber;

    @Expose
    @SerializedName("floor_id")
    private String floorId;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        notifyPropertyChanged(BR.roomNumber);
    }

    @Bindable
    public String getFoorId() {
        return floorId;
    }

    public void setFoorId(String foorId) {
        this.floorId = foorId;
        notifyPropertyChanged(BR.foorId);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", floorId='" + floorId + '\'' +
                '}';
    }
}
