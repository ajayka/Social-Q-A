package com.algorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.DAO.DataAceesLayer;
import com.model.Answers;
import com.model.Question;
import com.model.QuestionAndAnswerDetails;
import com.model.UserCommunityInfomation;

public class ResponseRate {
	private static final Logger logger = Logger.getLogger(ResponseRate.class);
	public static boolean ASC = true;
	public static boolean DESC = false;

	public static Map<Question, Double> ckeckResponceRate(int cid) {
//		double responceRate = 0;
		Map<Question, Double> responsemap = new HashMap<Question, Double>();
		List<QuestionAndAnswerDetails> qaList = DataAceesLayer.getQADetailsForResponseRate(cid);
		List<UserCommunityInfomation> allCommunityInfomation = DataAceesLayer.getAllUserCommunityInfomation(cid);
		int allfriends = allCommunityInfomation.size() - 1;

		for (QuestionAndAnswerDetails andAnswerDetails : qaList) {
			double responseRate=0.0;
			Question question = andAnswerDetails.getQuestion();
			List<Answers> answers = andAnswerDetails.getAnswerList();
			 responseRate = PrimaryStage.responceRate(answers.size(), allfriends);
			if(responseRate>0.0)
			responsemap.put(question, responseRate);
		}
		logger.debug("response rate of each question====> " + responsemap);
		
		Map<Question, Double> sortedMapAsc = sortByComparator(responsemap, DESC);

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
		DataAceesLayer.insertResponseRate(maxMap);
		return maxMap;
	}
	
	static Map<Question, Double> sortByComparator(Map<Question, Double> unsortMap, final boolean order) {

		List<Entry<Question, Double>> list = new LinkedList<Entry<Question, Double>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<Question, Double>>() {
			public int compare(Entry<Question, Double> o1, Entry<Question, Double> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<Question, Double> sortedMap = new LinkedHashMap<Question, Double>();
		for (Entry<Question, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
	
public static void main(String[] args) {
	ckeckResponceRate(6);
}

public static Map<Question, Double> ckeckResponceRate1(int cid) {
	// TODO Auto-generated method stub
	return null;
}
	
}



