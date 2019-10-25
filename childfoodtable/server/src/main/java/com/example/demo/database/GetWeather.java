package com.example.demo.database;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.example.demo.domain.WeatherData;
import com.example.demo.domain.WeatherDataList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetWeather {

	private Retrofit retrofit;
	private WeatherRestAPI restAPI;
	private String key = "S6Ec6FW0trxPWYg9EGCDKthUTROBvb0//6RLTVF6Iq8nYfpoN8sU1qqXPu4FZDQv";
	private Gson mGson;

	public GetWeather() {
		// key
		String URL = "https://data.kma.go.kr";

		retrofit = new Retrofit.Builder().baseUrl(URL).client(getUnsafeOkHttpClient().build())
				.addConverterFactory(GsonConverterFactory.create()).build();
		restAPI = retrofit.create(WeatherRestAPI.class);

		mGson = new GsonBuilder().setLenient().create();

	}

	public void searchWeather(String startDate, String endDate) {

		Call<ArrayList<WeatherDataList>> content = restAPI.getEvent("json", "ASOS", "DAY", startDate, endDate, "108", "10", "1",
				key);
		content.enqueue(new Callback<ArrayList<WeatherDataList>>() {
			@Override
			public void onResponse(Call<ArrayList<WeatherDataList>> call, Response<ArrayList<WeatherDataList>> response) {
				if (response.isSuccessful()) {
					ArrayList<WeatherDataList> tempcontent=(ArrayList<WeatherDataList>)response.body();
					System.out.println(response.body().toString());
					//System.out.println(tempcontent.get(0).getWeatherDataList().get(0).getAvg_cm10_te());

					//ArrayList<WeatherData> weatherDataList=(ArrayList<WeatherData>) weatherdatalist.get(3);
					//System.out.println(weatherdatalist.get(3));
					//ArrayList<WeatherData>aa = weatherdatalist.get(3);
					//ArrayList<WeatherData> weatherDataList = weatherdatalist.getWeatherDataList();
					//System.out.println(weatherDataList.get(1).getAvg_cm10_te());

				}
			}

			@Override
			public void onFailure(Call<ArrayList<WeatherDataList>> arg0, Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();

			}
		});

	}

	public static OkHttpClient.Builder getUnsafeOkHttpClient() {
		try {
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return builder;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
