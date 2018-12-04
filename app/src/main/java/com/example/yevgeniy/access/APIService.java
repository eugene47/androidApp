package com.example.yevgeniy.access;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface APIService {

    @Headers({
        "Content-Type: application/json",
    })

    @POST("api/login/basic")
    Call<User> userLogin(
            @Header("Authorization") String auth_key
    );

    @GET("api/user/info")
    Call<User> getInfo(
            @Header("Authorization") String token
    );

    @GET("api/day/current")
    Call<User> getCurrentDay(
            @Header("Authorization") String token
    );

    @GET("api/user/statistic")
    Call<User> getStatistic(
            @Header("Authorization") String token
    );
}
