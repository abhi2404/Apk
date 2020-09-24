package com.example.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface drop3 {


    @GET("StudentPortal/Student_Directory/")
    Call<Drop3Request> dropresponse(@Query("request_type" )String request_type,
                                          @Query("course") String course_type,
                                          @Query("branch") String branch_type);

}
