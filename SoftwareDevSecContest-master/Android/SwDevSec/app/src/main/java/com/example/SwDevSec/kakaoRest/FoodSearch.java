package com.example.SwDevSec.kakaoRest;

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

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodSearch {
    private RestAPI restAPI;
    private MapView mMapView;
    private String language;
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> items;
    private Context context;
    private Gson mGson;

    public FoodSearch(MyMapActivity mapActivity) {
        context = mapActivity;

        mMapView = ((MyMapActivity) context).getmMapView();
        language = ((MyMapActivity) context).getLanguage();
        adapter = ((MyMapActivity) context).getAdapter();
        listView = ((MyMapActivity) context).getListview();
        items = ((MyMapActivity) context).getItems();
        //retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restAPI = retrofit.create(RestAPI.class);

        //gson 설정
        mGson = new GsonBuilder()
                .setLenient()
                .create();

    }

    public void searchFood(double x, double y, String query) {
        if (x != 0 && y != 0 && query != null) {
            Call<RestaurantList> restaurant = restAPI.getRestaurant(query, Double.toString(x), Double.toString(y), Integer.toString(10000));
            restaurant.enqueue(new Callback<RestaurantList>() {
                @Override
                public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                    if (response.isSuccessful()) {
                        RestaurantList restaurantList = response.body();
                        DrawMarker drawMarker = new DrawMarker();
                        int result=drawMarker.makeMarker(restaurantList, mMapView);
                        if(result==-1){
                            Toast.makeText(context, "marker error", Toast.LENGTH_SHORT).show();
                        }
                        if (restaurantList != null) {
                            int listsize = restaurantList.getList().size();
                            if (listsize > 0) {
                                for (int i = 0; i < listsize; i++) {
                                    items.add(restaurantList.getList().get(i).getPlace_name());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }


                    }
                }

                @Override
                public void onFailure(Call<RestaurantList> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                    } else {
                        Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                    }
                }
            });
        }

    }

}



