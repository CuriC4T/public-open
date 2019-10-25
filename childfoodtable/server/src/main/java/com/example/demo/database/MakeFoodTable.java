package com.example.demo.database;

import java.util.Arrays;

public class MakeFoodTable extends Thread {
	int vegetabletemp[][] = new int[6][5];
	int meattemp[][] = new int[3][5];

	

	public MakeFoodTable() {

	}

	public void run() {

	}

	public double[] calcVegetablePrice(int[][] vegetableprice) {
		int j;
		double vegetableRatio[] = new double[] { 0, 0, 0, 0, 0, 0 };

		for (int i = 0; i < 6; i++) {
			for (j = 0; j < 5; j++) {
				vegetableRatio[i] = vegetableRatio[i] + vegetableprice[i][j];
			}
			vegetableRatio[i] = vegetableprice[i][j - 1] / (vegetableRatio[i] / 5);

		}
		return vegetableRatio;

	}

	public double[] calcMeatPrice(int[][] meatprice) {
		int j;
		double meatbleRatio[] = new double[] { 0, 0, 0 };

		for (int i = 0; i < 3; i++) {
			for (j = 0; j < 5; j++) {
				meatbleRatio[i] = meatbleRatio[i] + meatprice[i][j];

			}
			meatbleRatio[i] = meatprice[i][j - 1] / meatbleRatio[i];

		}
		return meatbleRatio;

	}

	public void makeTable(double vegetableRatio[], double meatbleRatio[]) {
		int minvegetable[];
		int minmeatble[];
		minvegetable = getMinIndex(vegetableRatio);
		minmeatble = getMinIndex(meatbleRatio);
		

	}

	public int[] sortMin(double array[]) {
		return null;
		
	}
	public int[] getMinIndex(double array[]) {
		double min[] = array;
		int index[] = new int[min.length];
//todo 정렬로 교체
		double tmpe;
		for (int i = 0; i < min.length; i++) {
			for(int j=0;j<min.length-i-1;j++) {
				if(min[j]>min[j+1]) {
					tmpe = min[j];
					min[j] = min[j+1];
					min[j+1] = tmpe;
				}
			}
			index[min.length-i-1]=i;
			
		}
//		if (min > array[i]) {
//		min2 = min;
//		min = array[i];
//		index[1] = index[0];
//		index[0] = i;
//
//	} else if (min2 < array[i] && array[i] != min2) {
//		min2 = array[i];
//
//	}
		return index;
	}

}
