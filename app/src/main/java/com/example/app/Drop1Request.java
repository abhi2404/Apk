package com.example.app;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drop1Request {
     @SerializedName("Status")
     @Expose
    private String Status;

     @SerializedName("data")
    @Expose
    private List<drop1data> data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public List<drop1data> getData() {
        return data;
    }

    public void setData(List<drop1data> data) {
        this.data = data;
    }
}
