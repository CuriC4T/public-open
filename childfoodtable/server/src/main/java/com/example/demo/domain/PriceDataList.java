package com.example.demo.domain;

import java.util.ArrayList;

public class PriceDataList {
	private boolean success;
	private ArrayList<PriceData> list;
	private int total_count;

	public ArrayList<PriceData> getList() {
		return list;
	}

	public void setList(ArrayList<PriceData> list) {
		this.list = list;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

}
