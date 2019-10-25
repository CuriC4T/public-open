package com.example.demo.database;


public enum Meat_Value {
	PIG(0), POLLACK(1), CHICKEN(2);
	int value;

	private Meat_Value(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static String getMeatName(int legIndex) {
		for (Meat_Value l : Meat_Value.values()) {
			if (l.value == legIndex)
				return l.name();
		}
		throw new IllegalArgumentException("Error index");
	}
}
