package com.example.lenovo.NanBada.post;

import android.os.AsyncTask;
import android.provider.Settings;
import android.widget.Toast;

import com.example.lenovo.NanBada.Nanbada.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class PostAsyncTask extends AsyncTask<String,String,String> {
    private MainActivity context;
    public PostAsyncTask(MainActivity context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {
        {
            String buf;
            LocateMe locateMe = new LocateMe(context);
            String position = locateMe.getLocation();
            if(position==null){
                position="noSetting";
            }
            String idByANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            System.out.println("asd"+idByANDROID_ID);

            try {
                Map<String, Object> params = new LinkedHashMap<>(); // 파라미터 세팅
                if (position != null) {
                    params.put("position", position);
                    params.put("user", idByANDROID_ID);
                }else{
                    params.put("position", null);

                }

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                System.out.println(postDataBytes);
                publishProgress(position);

                //URL uri = new URL("http://nyannyan.kro.kr:54545/hanium/post/");
                //URL uri = new URL("http://nyannyan.kro.kr:54545/receive_location");
                URL uri = new URL("http://172.30.1.8:2000/post");
                HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                OutputStream os = conn.getOutputStream();
                os.write(postDataBytes);
                os.flush();
                os.close();
                System.out.println("전송!!!!!!!");//EUC-KR
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"), conn.getContentLength());
// 표준출력으로 한 라인씩 출력
                while( ( buf = br.readLine() ) != null ) {
                    System.out.println( buf );
                }
// 스트림을 닫는다.
                // br.close();


            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (ProtocolException e1) {
               // e1.printStackTrace();
           } catch (MalformedURLException e1) {
              //e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    protected void onProgressUpdate(String ... values) {
        Toast toast = Toast.makeText(context, values[0], Toast.LENGTH_LONG);
        toast.show();

    }

}
