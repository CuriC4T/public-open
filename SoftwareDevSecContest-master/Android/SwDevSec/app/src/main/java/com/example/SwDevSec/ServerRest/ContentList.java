package com.example.SwDevSec.ServerRest;

import com.example.SwDevSec.kakaoRest.Restaurant;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Body;

public class ContentList {
    @SerializedName("response")
    private Response response;


    public class Response {

        @SerializedName("header")
        private Header header;
        @SerializedName("body")
        private Body body;


        public class Header {
            @SerializedName("resultCode")
            private String resultCode;
            @SerializedName("resultMsg")
            private String resultMsg;
        }

        public class Body {
            @SerializedName("items")
            private Items items;

            @SerializedName("totalCount")
            public String totalCount;


            public class Items {
                @SerializedName("item")
                List<Content> list = null;

                public List<Content> getList() {
                    List<Content> list = null;
                    if (this.list != null) {
                        list = this.list;
                    }
                    return list;
                }

            }

            public Items getItems() {
                Items items = null;
                if (this.items != null) {
                    items = this.items;
                }
                return items;
            }

            public String getTotalCount() {
                String totalCount = null;
                if (this.items != null) {
                    totalCount = this.totalCount;
                }
                return totalCount;
            }
        }

        public Body getBody() {
            Body body = null;
            if (this.body != null) {
                body = this.body;
            }
            return body;
        }
    }

    public Response getResponse() {

        Response response = null;
        if (this.response != null) {
            response = this.response;
        }
        return response;
    }

}