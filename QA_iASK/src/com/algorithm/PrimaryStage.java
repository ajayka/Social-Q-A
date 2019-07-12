package com.algorithm;

import java.util.Map;
import java.util.Map.Entry;

public class PrimaryStage {

	static int ackfi1;
	int qFi;

	public static double responceRate(int ackfi, int qfi) {
		double responseRate = 0;
		if (qfi > 0) {
			try {
				responseRate = (double) ackfi / (double) qfi;
//				//System.out.println("response Rate is ======> " + responseRate);
			} catch (ArithmeticException e) {
				//System.out.println("Enter valid number!!!!!!!");
				e.printStackTrace();
			}
		} else {
			responseRate = 0;
		}
		return responseRate;
	}

	@SuppressWarnings("unused")
	public static String mutualInteractionFrequency(Map<String, Integer> map) {
		double mutualinteraction = 0;
		int no_of_time = 0;
		int ackfi;
		mutualinteraction = ackfi1 / no_of_time;
		int maxVal = 0;
		String ids = null;
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (maxVal < entry.getValue()) {
				maxVal = entry.getValue();
				ids = entry.getKey();
			}
		}
		return ids;
	}

	public static double responseDelay(int ackfi, double delay_time) {
		double responseDelay = 0;
		responseDelay = delay_time / ackfi;

//		//System.out.println("Response Delay=========> " + responseDelay);
		return responseDelay;
	}

	public static double precisionRate(int avg_count_ans_score, int upper_count_ans_score) {

		double precisionRate = 0;

		precisionRate = avg_count_ans_score / upper_count_ans_score;

//		//System.out.println("precision rate =============> " + precisionRate);
		return precisionRate;

	}
}
