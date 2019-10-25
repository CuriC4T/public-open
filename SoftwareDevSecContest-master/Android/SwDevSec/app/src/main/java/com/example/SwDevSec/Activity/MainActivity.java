package com.example.SwDevSec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.SwDevSec.R;
import com.example.SwDevSec.ServerRest.EventSearch;

public class MainActivity extends AppCompatActivity {
    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String temp;

        mapButton = (Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 객체생성
                Intent intent = new Intent(getApplicationContext(), LangActivity.class);
                //Activity 실행메소드
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
