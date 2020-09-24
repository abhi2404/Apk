package com.example.app;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRequest {

    @POST("student_login/")
    Call<LoginResponse> requestResponse();

}

