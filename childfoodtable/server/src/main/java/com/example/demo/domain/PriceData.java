package com.example.demo.domain;

public class PriceData {
	private String day;
	private String type;
	private int price;

//    public PriceData(String code, String startdate,String enddate,String type) {
//        this.id = code;
//        this.startdate = startdate;
//        this.enddate=enddate;
//        this.type=type;
//        price="5000";
//    }

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
