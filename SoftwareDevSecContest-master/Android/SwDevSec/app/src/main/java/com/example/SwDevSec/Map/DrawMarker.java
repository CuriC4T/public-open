package com.example.SwDevSec.Map;

import com.example.SwDevSec.ServerRest.ContentList;
import com.example.SwDevSec.kakaoRest.RestaurantList;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class DrawMarker {
    private MapPOIItem mDefaultMarker;
    private String langs = "en";


    public DrawMarker() {
    }

    public int makeMarker(ContentList contentList, MapView mapView) {
        MapPoint DEFAULT_MARKER_POINT;
        String tempPoint;
        String tempTrans;
        int i = 0;

        String name = "Default";

        langs = Locale.getDefault().getLanguage(); //기기 언어 가져오기
        while (i < contentList.getResponse().getBody().getItems().getList().size()) {
            mDefaultMarker = new MapPOIItem();

            tempTrans = contentList.getResponse().getBody().getItems().getList().get(i).getTitle();
            name = (tempTrans != null) ? tempTrans : null;

            tempPoint = contentList.getResponse().getBody().getItems().getList().get(i).getMapy();
            String mapy = tempPoint != null ? tempPoint : null;
            tempPoint = contentList.getResponse().getBody().getItems().getList().get(i).getMapx();
            String mapx = tempPoint != null ? tempPoint : null;

            DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(Double.parseDouble(mapy), Double.parseDouble(mapx));
            if (!langs.equals("ko")) {//한국어가 아니면
                if (langs.equals("zh")) {//기기에서 중국어는 간체자 번체자 구분이 없어서 간체자로 설정해줬음
                    langs = "zh-CN";
                }
                try {
                    name = new Papago().execute(name, langs).get();//번역

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                } catch (ExecutionException e) {
                    return -1;
                }
            }
            //마커 세팅
            mDefaultMarker.setItemName(name);
            mDefaultMarker.setTag(0);
            mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);
            mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
            mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

            //마커 그리기
            mapView.addPOIItem(mDefaultMarker);
            mapView.selectPOIItem(mDefaultMarker, true);
            mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
            i++;
        }
        return 0;

    }

    public int makeMarker(RestaurantList restaurantList, MapView mapView) {
        MapPoint DEFAULT_MARKER_POINT;
        String tempPoint;
        String tempTrans;
        int tempsize;
        int i = 0;
        String name = "Default";

        langs = Locale.getDefault().getLanguage(); //기기 언어 가져오기
        //tempsize=restaurantList.getList().size()>0""
        int listsize=restaurantList.getList().size();

        if(listsize>0){
            while (i < listsize) {
                mDefaultMarker = new MapPOIItem();

                tempTrans = restaurantList.getList().get(i).getPlace_name();
                name = (tempTrans != null) ? tempTrans : null;

                tempPoint = restaurantList.getList().get(i).getY();
                String mapy = tempPoint != null ? tempPoint : null;
                tempPoint = restaurantList.getList().get(i).getX();
                String mapx = tempPoint != null ? tempPoint : null;

                DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(Double.parseDouble(mapy), Double.parseDouble(mapx));
                if (!langs.equals("ko")) {//한국어가 아니면
                    if (langs.equals("zh")) {//기기에서 중국어는 간체자 번체자 구분이 없어서 간체자로 설정해줬음
                        langs = "zh-CN";
                    }
                    try {
                        tempTrans = new Papago().execute(name, langs).get();
                        name = (tempTrans != null) ? tempTrans : null;

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return -1;
                    } catch (ExecutionException e) {
                        return -1;
                    }
                }
                //마커 세팅
                mDefaultMarker.setItemName(name);
                mDefaultMarker.setTag(0);
                mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);
                mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

                //마커 그리기
                mapView.addPOIItem(mDefaultMarker);
                mapView.selectPOIItem(mDefaultMarker, true);
                mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
                i++;
            }
        }


        return 0;
    }
}
