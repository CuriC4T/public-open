package com.example.SwDevSec.ServerRest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ServerRestAPI {
    @Headers({"Accept: application/json"})
    @GET("/SickYourCoding/map.do")
    Call<ContentList> getEvent(@Query("pageno") String pageno, @Query("mapx") String mapx, @Query("mapy") String mapy);
}
