package com.example.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Drop4Request {
    @SerializedName("sec")
    @Expose
    private ArrayList Sec;

    public ArrayList getSec() {
        return Sec;
    }

    public void setSec(ArrayList sec) {
        this.Sec = sec;
    }
}
