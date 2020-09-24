package com.example.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Drop3Request {
    @SerializedName("sem")
    @Expose
    private List Sem;

    public List getSem() {
        return Sem;
    }

    public void setSem(List sem) {
        this.Sem = sem;
    }
}
