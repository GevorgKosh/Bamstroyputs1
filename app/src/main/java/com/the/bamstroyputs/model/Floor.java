package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.the.bamstroyputs.BR;

public class Floor extends BaseObservable {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("room_count")
    private String roomNumber;

    public Floor() {
    }

    public Floor(String username, String roomNumber) {
        this.name = username;
        this.roomNumber = roomNumber;
    }

    public Floor(String id, String username, String roomNumber) {
        this.id = id;
        this.name = username;
        this.roomNumber = roomNumber;
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    @BindingAdapter({"imageUrl"})
//    public static void loadImage(ImageView view, String imageUrl) {
//        Glide.with(view.getContext())
//                .load(imageUrl)
//                .into(view);

        // If you consider Picasso, follow the below
        // Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(view);
//    }

    @Override
    public String toString() {
        return "Floor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }
}
