package com.example.demo.database;


public enum Vegetable_Value {
	RICE(0), ONION(1), NEUTALI(2), POTATO(3), MU(4), AEHOBAK(5);

	int value;

	private Vegetable_Value(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static String getVegetableName(int legIndex) {
		for (Vegetable_Value l : Vegetable_Value.values()) {
			if (l.value == legIndex)
				return l.name();
		}
		throw new IllegalArgumentException("Error index");
	}
}
