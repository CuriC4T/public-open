package com.example.lenovo.NanBada.post;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import android.util.Log;

import com.example.lenovo.NanBada.Nanbada.MainActivity;


import java.util.ArrayList;
import java.util.List;


public class LocateMe {
    private WifiManager mWifiManager;
    private Context context;
    private List<ScanResult> results;
    private int scanCount = 0;

    public LocateMe(MainActivity context) {
        this.context = context;


    }

    public String getLocation() {
        results = new ArrayList<>();
        mWifiManager = (WifiManager) context.getSystemService(context.getApplicationContext().WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
        ScheduleReader wifilist = new ScheduleReader();
        wifilist.run();
        scanCount = results.size();
        if (scanCount > 0) {
//            Log.v("TAG", "Wi-Fi Scan Results ... Count:" + scanCount);
//            for (int i = 0; i < scanCount; ++i) {
//                Log.v("TAG", "  SSID        =" + results.get(i).SSID);
//                Log.v("TAG", "  Level       =" + results.get(i).level);
//                Log.v("TAG", "---------------");
//            }
            return results.get(0).SSID;

        }
        return null;

        //return results.get(0).SSID.concat(String.valueOf(results.get(0).level));
    }


    class ScheduleReader implements Runnable {
        @Override
        public void run() {
            mWifiManager.startScan();
            if (mWifiManager.isWifiEnabled()) {
                results = mWifiManager.getScanResults();
            }
        }
    }
}

