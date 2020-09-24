package com.example.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface drop2 {

    @GET("StudentPortal/Student_Directory/")
    Call<List<Drop2Request>>dropresponse(@Query("request_type" )String request_type,
                                         @Query("course") String course_type);
}
