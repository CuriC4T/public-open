package com.example.todayfood.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ServerRestAPI {
    @Headers({"Accept: application/json"})
    @GET("/evewa/price")
    Call<PriceDataList> getEvent(@Query("startdate") String startdate, @Query("enddate") String enddate, @Query("type") String type);

}
