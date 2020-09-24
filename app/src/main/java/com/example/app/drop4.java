package com.example.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface drop4 {
    @GET("StudentPortal/Student_Directory/")
    Call<Drop4Request>dropresponse(@Query("request_type" )String request_type,
                                          @Query("course") String course_type,
                                          @Query("branch") String branch_type,
                                          @Query("sem") String sem_type);
}
