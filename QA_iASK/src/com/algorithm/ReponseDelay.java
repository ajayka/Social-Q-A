package com.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.DAO.DataAceesLayer;
import com.model.Answers;
import com.model.Question;
import com.model.QuestionAndAnswerDetails;

public class ReponseDelay {
	private static final Logger logger = Logger.getLogger(ReponseDelay.class);
	public static boolean ASC = true;
	public static boolean DESC = false;
	public static Map<Question, Double> responseDelay(int cid) {
		List<QuestionAndAnswerDetails> answerDetails = DataAceesLayer.getQADetailsForResponseRate(cid);

		Map<Question, Double> map = new HashMap<Question, Double>();

		for (QuestionAndAnswerDetails details : answerDetails) {
			Question question = details.getQuestion();

			List<Answers> answers = details.getAnswerList();
			long time = DataAceesLayer.getAvgrageResponseDelay(question.getQid());
			if (time > 0) {
				try {
					if (answers.size() > 0) {
						double avgmin = ((double) time / (double) 60) / (double) answers.size();
						double min=(double)avgmin/(double)100;
						map.put(question, min);
					}
				} catch (ArithmeticException e) {
					e.printStackTrace();
				}

			}
		}
		logger.debug("avg min time of each question is===> " + map);
		
		Map<Question, Double> sortedMapAsc=ResponseRate.sortByComparator(map,ASC);
		
		System.out.println(sortedMapAsc);
		Map<Question, Double> maxMap=new HashMap<Question,Double>();
		int count = 1;
		for (Entry<Question, Double> entry : sortedMapAsc.entrySet()) {
			if (count < 10) {
				maxMap.put(entry.getKey(), entry.getValue());
			} else {
				break;
			}
			count++;
		}
		return maxMap;
	}

	public static void main(String[] args) {

		responseDelay(6);
	}

}
