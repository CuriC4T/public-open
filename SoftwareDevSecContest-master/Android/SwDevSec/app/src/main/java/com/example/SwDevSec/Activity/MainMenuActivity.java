package com.example.SwDevSec.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SwDevSec.R;

public class MainMenuActivity extends AppCompatActivity {
    Button sightseeingButton;
    Button eventButton;
    Button restaurantButton;
    Button favoriteButton;
    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent preintent = getIntent();
        language=preintent.getExtras().getString("language");

        sightseeingButton = (Button) findViewById(R.id.sightseeingButton);
        eventButton = (Button) findViewById(R.id.eventButton);
        restaurantButton = (Button) findViewById(R.id.restaurantButton);
        favoriteButton = (Button) findViewById(R.id.favoriteButton);

        sightseeingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="sightseeing";
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("language",language);
                intent.putExtra("type",type);
                //Activity 실행메소드
                startActivity(intent);
            }
        });
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="event";
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("language",language);
                intent.putExtra("type",type);
                //Activity 실행메소드
                startActivity(intent);

            }
        });
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="restaurant";
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("language",language);
                intent.putExtra("type",type);
                //Activity 실행메소드
                startActivity(intent);

            }
        });
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="favorite";
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                intent.putExtra("language",language);
                intent.putExtra("type",type);
                //Activity 실행메소드
                startActivity(intent);

            }
        });
    }
}
