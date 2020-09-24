package com.example.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinalRequest {
    @SerializedName("uniq_id")
    @Expose
    private String Uniq_id;

    @SerializedName("mob")
    @Expose
    private String Mob;

    @SerializedName("class_roll_no")
    @Expose
    private String Rollno;

    @SerializedName("sem__sem")
    @Expose
    private String Sem;

    @SerializedName("section_section")
    @Expose
    private String Section;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("dept_detail__dept__value")
    @Expose
    private String Dept;

    @SerializedName("lib_id")
    @Expose
    private String Lib_id;

    @SerializedName("uni_roll_no")
    @Expose
    private String Uni_Rollno;

    @SerializedName("email_id")
    @Expose
    private String Email;

    @SerializedName("dob")
    @Expose
    private String DOB;

    public String getUniq_id() {
        return Uniq_id;
    }

    public void setUniq_id(String uniq_id) {
       this.Uniq_id = uniq_id;
    }

    public String getMob() {
        return Mob;
    }

    public void setMob(String mob) {
        this.Mob = mob;
    }

    public String getRollno() {
        return Rollno;
    }

    public void setRollno(String rollno) {
       this.Rollno = rollno;
    }

    public String getSem() {
        return Sem;
    }

    public void setSem(String sem) {
        this.Sem = sem;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        this.Section = section;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        this.Dept = dept;
    }

    public String getLib_id() {
        return Lib_id;
    }

    public void setLib_id(String lib_id) {
       this.Lib_id = lib_id;
    }

    public String getUni_Rollno() {
        return Uni_Rollno;
    }

    public void setUni_Rollno(String uni_Rollno) {
        this.Uni_Rollno = uni_Rollno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
