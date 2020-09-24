package com.example.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface drop1 {

    @POST("StudentPortal/Student_Directory/")
     Call<Drop1Request> dropresponse();
}
