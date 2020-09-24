package com.example.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drop2Request {
    @SerializedName("uid")
    @Expose
    private String Uid;

    @SerializedName("dept__value")
    @Expose
    private String Dept_value;

    @SerializedName("course__value")
    @Expose
    private String Course_value;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getDept_value() {
        return Dept_value;
    }

    public void setDept_value(String dept_value) {
        this.Dept_value = dept_value;
    }

    public String getCourse_value() {
        return Course_value;
    }

    public void setCourse_value(String course_value) {
        this.Course_value = course_value;
    }
}
