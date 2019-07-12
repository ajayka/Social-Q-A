package com.algorithm;


public class PricisionAndRecall {

	public static void pricisionAndRecall() {
		try {
			int Verynegative = 22;
			int negative = 16;
			int neural = 6;
			@SuppressWarnings("unused")
			int positive = 55;
			int veryPositive = 69;

			int TP = veryPositive;
			int FP = neural - negative;
			@SuppressWarnings("unused")
			double precision = 0;
			@SuppressWarnings("unused")
			double Recall = 0;
			if (TP + FP != 0) {
				precision = TP / TP + FP + 0.1;
			}

			int FN = Verynegative;
			if (TP + FN != 0) {
				Recall = TP / TP + FN + 0.1;
			}

			//DataAceesLayer.insertPricisionAndRecall(Math.abs(precision / 10), Math.abs(Recall / 10));
			// System.out.println(precision/10+" "+Recall/10);
		} catch (Exception e) {
//			System.out.println("Values Are Null");
		}
	}

	public static void main(String[] args) {
		pricisionAndRecall();
	}

}