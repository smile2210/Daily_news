package com.example.dailynews;

import com.example.dailynews.Model.MainData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("v2/top-headlines")
    Call<MainData> getMainData(@Query("country")String country,@Query("apiKey")String api);

    @GET("v2/top-headlines")
    Call<MainData> getBusinessData(@Query("country")String country, @Query("category")String category, @Query("apiKey")String api);
}
