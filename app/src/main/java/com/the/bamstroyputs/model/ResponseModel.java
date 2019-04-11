package com.the.bamstroyputs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel<E> {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("errors")
    @Expose
    private String errors;

    @SerializedName("data")
    @Expose
    private E data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
