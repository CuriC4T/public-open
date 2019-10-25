package com.example.SwDevSec.kakaoRest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList {
    private String category;
    @SerializedName("documents")
    private List<Restaurant> list = null;


    public List<Restaurant> getList() {
        List<Restaurant> list = null;
        if (this.list != null) {
            list = this.list;
        }
        return list;
    }


}
