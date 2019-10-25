package com.example.SwDevSec.ServerRest;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.SwDevSec.Activity.MyMapActivity;
import com.example.SwDevSec.Map.DrawMarker;
import com.example.SwDevSec.kakaoRest.RestAPI;
import com.example.SwDevSec.kakaoRest.RestaurantList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventSearch {

    private Retrofit retrofit;
    private ServerRestAPI restAPI;
    private MapView mMapView;
    private String language;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> items;
    private Context context;
    private Gson mGson;

    public EventSearch(MyMapActivity mapActivity) {
        context = mapActivity;

        mMapView = ((MyMapActivity) context).getmMapView();
        language = ((MyMapActivity) context).getLanguage();
        adapter = ((MyMapActivity) context).getAdapter();
        listView = ((MyMapActivity) context).getListview();
        items = ((MyMapActivity) context).getItems();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.231.70.45:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restAPI = retrofit.create(ServerRestAPI.class);


        //gson 설정
        mGson = new GsonBuilder()
                .setLenient()
                .create();
    }

    public void searchEvent(double mapx, double mapy) {
        if (mapx != 0 && mapy != 0) {
            Call<ContentList> content = restAPI.getEvent(Integer.toString(1), Double.toString(mapx), Double.toString(mapy));
            content.enqueue(new Callback<ContentList>() {
                @Override
                public void onResponse(Call<ContentList> call, Response<ContentList> response) {
                    if (response.isSuccessful()) {
                        ContentList contentList = (ContentList)response.body();
                        System.out.println(contentList.getResponse().getBody().getItems().getList().size());
                        DrawMarker drawMarker = new DrawMarker();
                        drawMarker.makeMarker(contentList, mMapView);
                        if(contentList!=null){
                            int listsize=contentList.getResponse().getBody().getItems().getList().size();
                            for (int i = 0; i < listsize; i++) {
                                items.add(contentList.getResponse().getBody().getItems().getList().get(i).getTitle());
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                }


                @Override
                public void onFailure(Call<ContentList> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                    }
                    else {
                        Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                    }
                }
            });
        }


    }

}