package com.example.SwDevSec.Map;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Papago extends AsyncTask<String, Integer, String> {
    //AsyncTask <Params, Progress, Result>
    //
    //Params : doInBackground 파라미터 타입이 되며, execute 메소드 인자 값이 됩니다.
    //
    //Progress : doInBackground 작업 시 진행 단위의 타입으로 onProgressUpdate 파라미터 타입입니다.
    //
    //Result : doInBackground 리턴값으로 onPostExecute 파라미터 타입입니다.


    private String result;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    public String doInBackground(String... arg0) {
        String clientId = "@string/clientId";
        String clientSecret = "@string/clientSecret";
        String text = arg0[0];
        String langs = arg0[1];
        try {
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            text = URLEncoder.encode(text, "UTF-8");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            //번역할 문장
            String postParams = "source=ko&target=" + langs + "&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            //번역 한거 가져오기
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            result = response.toString();
            JSONObject object = new JSONObject(result);

            String n = object.getString("message");
            object = new JSONObject(n);
            String p = object.getString("result");
            object = new JSONObject(p);
            result = object.getString("translatedText");
        } catch (IOException e) {
            return "error";
        }catch (JSONException e) {
            return "error";
        }
        return result;
    }

    @Override
    protected void onPostExecute(String a) {
        super.onPostExecute(a);

    }
}
