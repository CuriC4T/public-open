package com.example.SwDevSec.ServerRest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("addr1")
    private String addr1;

    @SerializedName("addr2")
    private String addr2;

    @SerializedName("areacode")
    private String areacode;

    @SerializedName("cat1")
    private String cat1;

    @SerializedName("cat2")
    private String cat2;

    @SerializedName("cat3")
    private String cat3;

    @SerializedName("contentid")
    private String contentid;

    @SerializedName("contenttypeid")
    private String contenttypeid;

    @SerializedName("createdtime")
    private String createdtime;

    @SerializedName("dist")
    private String dist;

    @SerializedName("firstimage")
    private String firstimage;

    @SerializedName("firstimage2")
    private String firstimage2;

    @SerializedName("mapx")
    private String mapx;

    @SerializedName("mapy")
    private String mapy;

    @SerializedName("mlevel")
    private String mlevel;

    @SerializedName("modifiedtime")
    private String modifiedtime;

    @SerializedName("readcount")
    private String readcount;

    @SerializedName("sigungucode")
    private String sigungucode;

    @SerializedName("tel")
    private String tel;

    @SerializedName("title")
    private String title;


    public String getMapx() {
        String mapx = null;
        if (this.mapx != null) {
            mapx = this.mapx;
        }
        return mapx;
    }


    public String getMapy() {

        String mapy = null;
        if (this.mapy != null) {
            mapy = this.mapy;
        }
        return mapy;

    }


    public String getTitle() {

        String title = null;
        if (this.title != null) {
            title = this.title;
        }
        return title;
    }


}
