package com.the.bamstroyputs.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.the.bamstroyputs.BR;

public class GeneralItem extends BaseObservable {
    private String title;
    private String icon;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR.icon);
    }
}
