package com.example.demo.database;

public class PriceDTO {
	private String date;
	private int pig;
	private int rice;
	private int onion;
	private int pollack;
	private int neutali;
	private int chicken;
	private int potato;
	private int mu;
	private int aehobak;
	private int price[][]= new int[9][5];

	public int[][] getPrice() {
		return price;
	}

	public void setPrice(int[][] price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPig() {
		return pig;
	}

	public void setPig(int pig) {
		this.pig = pig;
	}

	public int getRice() {
		return rice;
	}

	public void setRice(int rice) {
		this.rice = rice;
	}

	public int getOnion() {
		return onion;
	}

	public void setOnion(int onion) {
		this.onion = onion;
	}

	public int getPollack() {
		return pollack;
	}

	public void setPollack(int pollack) {
		this.pollack = pollack;
	}

	public int getNeutali() {
		return neutali;
	}

	public void setNeutali(int neutali) {
		this.neutali = neutali;
	}

	public int getChicken() {
		return chicken;
	}

	public void setChicken(int chicken) {
		this.chicken = chicken;
	}

	public int getPotato() {
		return potato;
	}

	public void setPotato(int potato) {
		this.potato = potato;
	}

	public int getMu() {
		return mu;
	}

	public void setMu(int mu) {
		this.mu = mu;
	}

	public int getAehobak() {
		return aehobak;
	}

	public void setAehobak(int aehobak) {
		this.aehobak = aehobak;
	}

}
