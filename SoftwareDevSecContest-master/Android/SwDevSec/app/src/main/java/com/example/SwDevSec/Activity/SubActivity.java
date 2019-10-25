package com.example.SwDevSec.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SwDevSec.R;

import net.daum.android.map.MapActivity;

public class SubActivity extends AppCompatActivity {
    private String language;
    private String type;
    private String typeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent preintent = getIntent();
        language = preintent.getExtras().getString("language");
        type = preintent.getExtras().getString("type");

        switch (type) {
            case "sightseeing":
                typeCode = "12";
                break;
            case "event":
                typeCode = "15";
                break;
            case "restaurant":
                typeCode = "39";
                break;
            case "favorite":
                typeCode = "12";
                break;
            default:
                break;
        }

        ImageButton nationwideButton = (ImageButton) findViewById(R.id.nationwideButton);
        ImageButton nearButton = (ImageButton) findViewById(R.id.nearButton);
//        ImageButton nationwideButton = (ImageButton) findViewById(R.id.nationwideButton);
//        ImageButton nearButton = (ImageButton) findViewById(R.id.nearButton);

        nationwideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), NationwideActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("typeCode", typeCode);
                //Activity 실행메소드
                startActivity(intent);

            }
        });
        nearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), MyMapActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("type", type);
                //Activity 실행메소드
                startActivity(intent);

            }
        });


    }
}