package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class FoodDataList {
	private boolean success;
	private ArrayList<FoodData> list;
	private int total_count;
	
	public ArrayList<FoodData> getList() {
		return list;
	}

	public void setList(ArrayList<FoodData> list) {
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
