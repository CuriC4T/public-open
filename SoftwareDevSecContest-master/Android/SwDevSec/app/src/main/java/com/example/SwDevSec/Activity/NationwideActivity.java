package com.example.SwDevSec.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SwDevSec.R;

public class NationwideActivity extends AppCompatActivity {
    private String language;
    private String typeCode;
    private String areacode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nationwide);
        Intent preintent = getIntent();
        language = preintent.getExtras().getString("language");
        typeCode = preintent.getExtras().getString("typeCode");

        ImageButton localButton1 = (ImageButton) findViewById(R.id.localButton1);
        ImageButton localButton2 = (ImageButton) findViewById(R.id.localButton2);
        ImageButton localButton3 = (ImageButton) findViewById(R.id.localButton3);
        ImageButton localButton4 = (ImageButton) findViewById(R.id.localButton4);
        ImageButton localButton5 = (ImageButton) findViewById(R.id.localButton5);
        ImageButton localButton6 = (ImageButton) findViewById(R.id.localButton6);
        ImageButton localButton7 = (ImageButton) findViewById(R.id.localButton7);
        ImageButton localButton8 = (ImageButton) findViewById(R.id.localButton8);
        ImageButton localButton9 = (ImageButton) findViewById(R.id.localButton9);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                switch (v.getId()) {
                    //서울 1, 인천 2, 대전 3, 대구 4, 광주 5, 울산 6, 부산 7, 세종시 8
                    //경기도 31, 강원도 32, 충북 33, 충남 34,
                    //경북 35, 경남 36, 전북 37, 전남 38, 제주 39
                    case R.id.localButton1:
                        areacode = "32"; //강원
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton2:
                        areacode = "37"; //전북
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton3:
                        areacode = "38"; //전남
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton4:
                        areacode = "36"; //경남
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton5:
                        areacode = "35"; //경북
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton6:
                        areacode = "31"; //경기도
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton7:
                        areacode = "34"; //충남
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton8:
                        areacode = "33"; //충북
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    case R.id.localButton9:
                        areacode = "39"; //제주
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                    default:
                        areacode = "경기도";
                        intent.putExtra("language",language);
                        intent.putExtra("typeCode",typeCode);
                        intent.putExtra("areacode",areacode);
                        break;
                }
                startActivity(intent);
            }
        };
        localButton1.setOnClickListener(onClickListener);
        localButton2.setOnClickListener(onClickListener);
        localButton3.setOnClickListener(onClickListener);
        localButton4.setOnClickListener(onClickListener);
        localButton5.setOnClickListener(onClickListener);
        localButton6.setOnClickListener(onClickListener);
        localButton7.setOnClickListener(onClickListener);
        localButton8.setOnClickListener(onClickListener);
        localButton9.setOnClickListener(onClickListener);
    }
}