package com.example.SwDevSec.kakaoRest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestAPI {
    @Headers("Authorization: KakaoAK bc5cf1cb25ded888e1fb751eb0fdb36f")
    @GET("/v2/local/search/keyword.json")
    Call<RestaurantList> getRestaurant(@Query("query") String query, @Query("y") String y, @Query("x") String x, @Query("radius") String radius);

}
