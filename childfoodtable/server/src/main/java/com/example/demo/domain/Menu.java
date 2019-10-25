package com.example.demo.domain;

public class Menu {
	private String date;
	private String meatfood;
	private String vegetablefood1;
	private String vegetablefood2;

	public Menu() {
		
	}
	public Menu(String date,String meatfood,String vegetablefood1,String vegetablefood2) {
		this.date=date;
		this.meatfood=meatfood;
		this.vegetablefood1=vegetablefood1;
		this.vegetablefood2=vegetablefood2;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMeatfood() {
		return meatfood;
	}

	public void setMeatfood(String meatfood) {
		this.meatfood = meatfood;
	}

	public String getVegetablefood1() {
		return vegetablefood1;
	}

	public void setVegetablefood1(String vegetablefood1) {
		this.vegetablefood1 = vegetablefood1;
	}

	public String getVegetablefood2() {
		return vegetablefood2;
	}

	public void setVegetablefood2(String vegetablefood2) {
		this.vegetablefood2 = vegetablefood2;
	}

}