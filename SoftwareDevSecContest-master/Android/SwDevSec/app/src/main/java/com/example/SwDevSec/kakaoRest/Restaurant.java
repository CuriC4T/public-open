package com.example.SwDevSec.kakaoRest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("address_name")
    private String address_name;

    @SerializedName("category_group_code")
    private String category_group_code;

    @SerializedName("category_group_name")
    private String category_group_name;

    @SerializedName("category_name")
    private String category_name;

    @SerializedName("distance")
    private String distance;

    @SerializedName("id")
    private String id;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("place_name")
    private String place_name;

    @SerializedName("place_url")
    @Expose
    private String place_url;

    @SerializedName("road_address_name")
    private String road_address_name;

    @SerializedName("x")
    private String x;

    @SerializedName("y")
    private String y;

    public void setId(String id) {
        if(id!=null){
            this.id = id;
        }
    }

    public String getId() {
        String id = null;
        if (this.id != null) {
            id = this.id;
        }
        return id;
    }

    public String getPlace_name() {
        String place_name = null;
        if (this.place_name != null) {
            place_name = this.place_name;
        }
        return place_name;
    }

    public String getX() {
        String x = null;
        if (this.x != null) {
            x = this.x;
        }
        return x;
    }

    public String getY() {

        String y = null;
        if (this.y != null) {
            y = this.y;
        }
        return y;
    }
}
