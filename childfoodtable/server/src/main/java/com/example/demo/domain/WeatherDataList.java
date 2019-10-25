package com.example.demo.domain;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WeatherDataList {
	
	
	@SerializedName("Status")
	public int status;


	private StdIds stdIds;

	@SerializedName("info")
	private ArrayList<WeatherData> weatherDataList;

	
	@SerializedName("msg")
	private String msg;
	

	public class StdIds {
		@SerializedName("stdIds")
		private String stdIds;

		public String getStdIds() {
			return stdIds;
		}

		public void setStdIds(String stdIds) {
			this.stdIds = stdIds;
		}

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public StdIds getStdIds() {
		return stdIds;
	}

	public void setStdIds(StdIds stdIds) {
		this.stdIds = stdIds;
	}

	public ArrayList<WeatherData> getWeatherDataList() {
		return weatherDataList;
	}

	public void setWeatherDataList(ArrayList<WeatherData> weatherDataList) {
		this.weatherDataList = weatherDataList;
	}

}
