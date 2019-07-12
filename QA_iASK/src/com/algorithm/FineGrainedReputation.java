package com.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.DAO.DataAceesLayer;
import com.model.Answers;
import com.model.Question;
import com.model.QuestionAndAnswerDetails;
import com.model.Rating;
import com.model.UserCommunityInfomation;

public class FineGrainedReputation {
	private static final Logger logger = Logger.getLogger(FineGrainedReputation.class);
	public static boolean ASC = true;
	public static boolean DESC = false;

	/**
	 * 
	 * Input: reputation of users Output: Representation of users(expert). Step
	 * 1: Root server calculate global reputation of users fan. Step2: Use Buj
	 * to calculate % of best answers from the answers. Step 3: Virtual server
	 * calculation. Step 4: Reputation of uj Step 5: Find top answers and if not
	 */

	public static List<QuestionAndAnswerDetails> relatedQuestion(String question, int cid) {
		// before starting algorithm we are going to calculate avg rating of
		// every answer with different users
		// that are update into answer table for reputation purpose
		List<Rating> ratingList = DataAceesLayer.getAllRatingForAvg();
		for (Rating rating : ratingList) {
			List<Rating> aidWize = DataAceesLayer.getSingleRating(rating.getAid());
			int avg = 0;
			for (Rating rating2 : aidWize) {
				avg += rating2.getRating();
			}
			//System.out.println("Avg rating is =====> " + avg);

			// update value into answer table
			Boolean flag = DataAceesLayer.updateAnswerRating(rating.getAid(), avg / aidWize.size());

			if (flag)
				logger.debug("updated sucessfully");
			else {
				logger.debug("updated failed");
				break;
			}
		}
		
		List<QuestionAndAnswerDetails> questionAndAnswerDetails = DataAceesLayer.getQADetailsForResponseRate(cid);
		//find max rated answer for reward system
		@SuppressWarnings("unused")
		List<UserCommunityInfomation> list=DataAceesLayer.getAllUserCommunityInfomation(cid);
		//this is temp table each and every time recalculating best answerer
		DataAceesLayer.truncateReward();
		for(QuestionAndAnswerDetails rewardpurpose:questionAndAnswerDetails)
		{
			Answers ans=DataAceesLayer.getMaxRatingForReward(rewardpurpose.getQuestion().getQid());
			if(ans.getRating()==5){
			ans.setBal(10);
			DataAceesLayer.insertReward(ans);
			}else if(ans.getRating()==4){
				ans.setBal(7);
				DataAceesLayer.insertReward(ans);
			}else if(ans.getRating()==3){
				ans.setBal(5);
				DataAceesLayer.insertReward(ans);
			}
		}

		// Step 1: Root server calculate global reputation of users fan.
		List<String> withoutStopwords = QuestionForworded.removeStopwords(question);
//		System.out.println(withoutStopwords);
		Map<Integer, List<String>> questionWords = new HashMap<Integer, List<String>>();
		Set<Integer> qids = new HashSet<Integer>();
		List<QuestionAndAnswerDetails> answerDetailsList = new ArrayList<QuestionAndAnswerDetails>();

		for (QuestionAndAnswerDetails details : questionAndAnswerDetails) {
			Question que = details.getQuestion();
			questionWords.put(que.getQid(), QuestionForworded.removeStopwords(que.getQuestion()));
		}
		// Step2: Use Buj to calculate % of best answers from the answers.
		for (Entry<Integer, List<String>> entry : questionWords.entrySet()) {
			for (String word : entry.getValue()) {
				if (withoutStopwords.contains(word)) {
					qids.add(entry.getKey());
				}
			}
		}
		// Step 3: Virtual server calculation.
		// System.out.println("List of selected questions =======> "+qids);
		logger.debug("List of selected questions =======> " + qids);
		List<QuestionAndAnswerDetails> selectedQuestion = DataAceesLayer.getSelectedQADetails(qids);
		// Step 4: Reputation of uj
		// System.out.println(qids.size() + " and " + selectedQuestion.size());
		logger.debug("Size of selectecd question " + qids.size() + " and   " + selectedQuestion.size());

		// Step 5: Find top answers
		Map<Question, Map<Integer, Double>> reputationMap = new HashMap<Question, Map<Integer, Double>>();
		for (QuestionAndAnswerDetails questionAndAnswerDetails2 : selectedQuestion) {

			Map<Integer, Double> avgMap = new HashMap<Integer, Double>();
			Question que = questionAndAnswerDetails2.getQuestion();
			List<Answers> answerList = questionAndAnswerDetails2.getAnswerList();
			for (Answers ans : answerList) {

				List<Rating> ratingOfSingleAns = DataAceesLayer.getSingleRating(ans.getAid());
				@SuppressWarnings("unused")
				Rating rating = new Rating();
				int temp = 0;
				for (Rating rate : ratingOfSingleAns) {
					temp = temp + rate.getRating();
					// System.out.println("Rating in fine == >
					// "+rate.getRating());
				}
				double avgRating = 0;
				int count = DataAceesLayer.getCountRate(ans.getAid());
				logger.debug(
						"count is  ==== > " + count + "  ans.getAid() === " + ans.getAid() + " temp is === > " + temp);
				if (count > 0) {
					avgRating = (double) temp / (double) DataAceesLayer.getCountRate(ans.getAid());

					@SuppressWarnings("unused")
					boolean flag = DataAceesLayer.updateAnswerRating(ans.getAid(), avgRating);
					avgMap.put(ans.getAid(), avgRating);

				}
				logger.debug("Avg map size is  ==== > " + avgMap.size() + "  temp (avg rating)===> " + avgRating);
			}

			Map<Integer, Double> sortedMapAsc = sortByComparator(avgMap, DESC);
			reputationMap.put(que, sortedMapAsc);

		}
		logger.debug("Repuration map size is=====> " + reputationMap);

		for (Entry<Question, Map<Integer, Double>> entry : reputationMap.entrySet()) {
			List<Answers> ansList = new ArrayList<Answers>();
			QuestionAndAnswerDetails details = new QuestionAndAnswerDetails();
			details.setQuestion(entry.getKey());
			@SuppressWarnings("unused")
			Question q = entry.getKey();
			Map<Integer, Double> ansmap = entry.getValue();
			for (Entry<Integer, Double> entry1 : ansmap.entrySet()) {
				Answers answers = DataAceesLayer.getSingleAnswer(entry1.getKey());
				ansList.add(answers);
			}
			details.setAnswerList(ansList);
			answerDetailsList.add(details);
		}
		logger.debug("Size of question ans answer with his rating " + answerDetailsList.size());

		@SuppressWarnings("unused")
		RewardCalculation rewardCalculation=new RewardCalculation(cid);
		return answerDetailsList;
	}

	private static Map<Integer, Double> sortByComparator(Map<Integer, Double> unsortMap, final boolean order) {

		List<Entry<Integer, Double>> list = new LinkedList<Entry<Integer, Double>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<Integer, Double>>() {

			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<Integer, Double> sortedMap = new LinkedHashMap<Integer, Double>();
		for (Entry<Integer, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (@SuppressWarnings("unused") Entry<String, Integer> entry : map.entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}

	public static void main(String[] args) {
		relatedQuestion("what is object? run java jet interface", 6);
	}
}
