package com.example.demo.database;

import java.util.ArrayList;

import com.example.demo.domain.WeatherDataList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherRestAPI {

	//@Headers({ "Accept: application/json" })
	@GET("/apiData/getData")
	Call<ArrayList<WeatherDataList>> getEvent(@Query("type") String type,
			@Query("dataCd") String dataCd, @Query("dateCd") String dateCd, @Query("startDt") String startDt,
			@Query("endDt") String endDt, @Query("stnIds") String stnIds, @Query("schListCnt") String schListCnt,
			@Query("pageIndex") String pageIndex,@Query("apiKey") String apiKey);

}
