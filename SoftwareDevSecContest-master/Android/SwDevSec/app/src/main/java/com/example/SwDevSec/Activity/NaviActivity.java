package com.example.SwDevSec.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.SwDevSec.R;
import com.kakao.kakaonavi.KakaoNaviParams;
import com.kakao.kakaonavi.KakaoNaviService;
import com.kakao.kakaonavi.Location;
import com.kakao.kakaonavi.NaviOptions;
import com.kakao.kakaonavi.options.CoordType;
import com.kakao.kakaonavi.options.RpOption;
import com.kakao.kakaonavi.options.VehicleType;

public class NaviActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        Intent preintent = getIntent();
        double pointX = preintent.getExtras().getDouble("X");
        double pointY = preintent.getExtras().getDouble("Y");
        String destinationName = preintent.getExtras().getString("Destination");
        double latitude = preintent.getExtras().getDouble("Latitude");
        double longitude = preintent.getExtras().getDouble("Longitude");

        if (destinationName != null) {
            navigation(pointX, pointY, destinationName, latitude, longitude);

        }

    }

    public void navigation(double pointX, double pointY, String destinationName, double latitude, double longitude) {
        Location destination = Location.newBuilder(destinationName, longitude, latitude).build();
        NaviOptions options = NaviOptions.newBuilder().setCoordType(CoordType.WGS84).setVehicleType(VehicleType.FIRST).setRpOption(RpOption.SHORTEST).setStartX(pointX).setStartY(pointY).build();
        KakaoNaviParams.Builder builder = KakaoNaviParams.newBuilder(destination).setNaviOptions(options);
        KakaoNaviService.getInstance().navigate(NaviActivity.this, builder.build());
    }
}
