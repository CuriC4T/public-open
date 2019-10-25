package com.example.todayfood.rest;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todayfood.ui.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceSearch {
    private Retrofit retrofit;
    private ServerRestAPI restAPI;
    private Context context;

    private Button sbutton;
    private Button ebutton;
    private Button tbutton;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    public PriceSearch(MainActivity mainActivity) {
        context = mainActivity;

        sbutton = ((MainActivity) context).getSbutton();

        textView4 = ((MainActivity) context).getTextView4();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://nyannyan.kro.kr:54545")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restAPI = retrofit.create(ServerRestAPI.class);
    }

    public void getPrice(String startdate, String enddate, String typedate) {
        Call<PriceDataList> content = restAPI.getEvent(startdate, enddate, typedate);
        content.enqueue(new Callback<PriceDataList>() {
            @Override
            public void onResponse(Call<PriceDataList> call, Response<PriceDataList> response) {
                PriceDataList contentList = (PriceDataList) response.body();
                int size =contentList.getTotal_count();
                textView4.setText("");
                    for (int i = 0; i < size-1; i++) {

                        textView4.append(
                                "날짜:"+
                                contentList.getList().get(i).getDay()+" "+
                                contentList.getList().get(i).getType()+":"+
                                contentList.getList().get(i).getPrice()+"\n"
                        );
                    //items.add(contentList.getResponse().getBody().getItems().getList().get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<PriceDataList> call, Throwable t) {
                if (t instanceof IOException) {
                    //Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    //Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });


    }
}
