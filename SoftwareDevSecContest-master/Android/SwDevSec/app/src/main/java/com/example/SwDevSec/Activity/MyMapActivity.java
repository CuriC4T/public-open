package com.example.SwDevSec.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.SwDevSec.ServerRest.EventSearch;
import com.example.SwDevSec.kakaoRest.FoodSearch;
import com.example.SwDevSec.Map.CustomCalloutBalloonAdapter;
import com.example.SwDevSec.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


public class MyMapActivity extends MapBaseActivity implements
        MapView.MapViewEventListener,
        MapView.POIItemEventListener {
    private static final String LOG_TAG = "Map";

    private String language = null;
    private String type;

    private ArrayList<String> items;
    private ListView listview;
    private FoodSearch foodsearch;
    private ArrayAdapter adapter;

    private EditText editText;
    private String destination = null;
    private double latitude;
    private double longitude;
    private Button buttonSearch;
    private Button navibutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent preintent = getIntent();
        language = preintent.getExtras().getString("language");
        type = preintent.getExtras().getString("type");

        if (language != null && type != null) {
            mMapView = (MapView) findViewById(R.id.map_view);
            mMapView.setMapViewEventListener(this);
            mMapView.setPOIItemEventListener(this);
            mMapView.setCurrentLocationEventListener(this);
            mMapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());

            if (!checkLocationServicesStatus()) {

                showDialogForLocationServiceSetting();
            } else {

                checkRunTimePermission();
            }
            editText = (EditText) findViewById(R.id.editTextQuery);
            buttonSearch = (Button) findViewById(R.id.buttonSearch);
            navibutton = (Button) findViewById(R.id.navibutton);
            listview = (ListView) findViewById(R.id.listview1);
            items = new ArrayList<String>();
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
            listview.setAdapter(adapter);

        }
    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {
                Log.d("@@@", "start");
                //위치 값을 가져올 수 있음
                mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                    Toast.makeText(MyMapActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(MyMapActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MyMapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음
            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyMapActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MyMapActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MyMapActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MyMapActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyMapActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {


        foodsearch = new FoodSearch(this);
        EventSearch eventSearch = new EventSearch(this);
        //eventSearch.searchEvent(127.0,37.5);
        switch (type) {
            case "sightseeing":
                eventSearch.searchEvent(x, y);
                editText.setText("관광");
                break;
            case "event":
                eventSearch.searchEvent(x, y);
                editText.setText("행사");
                break;
            case "restaurant":
                editText.setText("맛집");
                break;
            default:
                break;
        }
        buttonSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapView.removeAllPOIItems();
                items.clear();
                String query = editText.getText().toString();
                foodsearch.searchFood(x, y, query);

            }
        });
        navibutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (destination != null) {
                    Intent intent = new Intent(getApplicationContext(), NaviActivity.class);
                    intent.putExtra("X", x);
                    intent.putExtra("Y", y);
                    intent.putExtra("Destination", destination);
                    intent.putExtra("Latitude", latitude);
                    intent.putExtra("Longitude", longitude);
                    startActivity(intent);
                }


            }
        });
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//
//                System.out.println(position);
//            }
//
//
//        });
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        if (mapView != null && mapPOIItem != null) {
            destination = mapPOIItem.getItemName();
            longitude = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;
            latitude = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        }


    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
    }

    public String getLanguage() {
        String language = null;
        if (this.language != null) {
            language = this.language;
        }
        return language;

    }

    public ListView getListview() {
        ListView listview = null;
        if (this.listview != null) {
            listview = this.listview;
        }
        return listview;

    }

    public ArrayList<String> getItems() {
        ArrayList<String> items = null;
        if (this.items != null) {
            items = this.items;
        }
        return items;

    }

    public ArrayAdapter getAdapter() {
        ArrayAdapter adapter = null;
        if (this.adapter != null) {
            adapter = this.adapter;
        }
        return adapter;

    }


}
