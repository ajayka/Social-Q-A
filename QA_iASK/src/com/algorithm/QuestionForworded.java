package com.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.DAO.DataAceesLayer;
import com.model.Answers;
import com.model.Category;
import com.model.PriviousQuestion;
import com.model.QuestionAndAnswerDetails;
import com.model.Subcategory;

public class QuestionForworded {
	private static final Logger logger = Logger.getLogger(QuestionForworded.class);

	public static Map<Integer, Integer> fordwordQustion(PriviousQuestion priviousQuestion) {
		Map<Integer, Integer> totalCountMap = new HashMap<Integer, Integer>();
		Map<Integer, List<Subcategory>> map = new HashMap<Integer, List<Subcategory>>();
		Map<Integer, Map<Integer, Integer>> mapCount = new HashMap<Integer, Map<Integer, Integer>>();

		mapCount.clear();
		totalCountMap.clear();

		/**
		 * map for fetch all category and sub category...
		 */

		List<Category> categorylist = DataAceesLayer.getAllCategory();

		List<String> remainingQuestioWords = QuestionForworded.removeStopwords(priviousQuestion.getQuestion());
		/**
		 * in map cid and list of subcategories are present
		 */

		for (Category category : categorylist) {
			map.put(category.getCid(), DataAceesLayer.getAllSubCategory(category.getCid()));
		}

		for (Entry<Integer, List<Subcategory>> entry : map.entrySet()) {
			Map<Integer, Integer> submapCount = new HashMap<Integer, Integer>();
			int cid = entry.getKey();
			int count1 = 0;
			logger.debug("Cid in map= :" + cid);
			String cidName = DataAceesLayer.getCategoryName(cid);
			logger.debug("Cid in map= :" + cid + " and name is " + cidName);
			List<Subcategory> subList = entry.getValue();
			logger.debug("Sublist size  =  " + subList.size());

			for (Subcategory subcategory : subList) {
				// only chk sub categories present in entered question

				logger.debug(
						"Sub categories and main categories ====> " + subcategory.getSubcategoryName().toLowerCase()
								+ "     " + DataAceesLayer.getCategoryName(cid).toLowerCase());
				if (remainingQuestioWords.contains(DataAceesLayer.getCategoryName(cid).toLowerCase())) {
					count1++;
				}

				if (remainingQuestioWords.contains(subcategory.getSubcategoryName())) {
					count1++;
				}

				int subid = subcategory.getSubid();
				logger.debug("subid and sub name  ====> " + subid + "   " + "Sub category name  ====> "
						+ subcategory.getSubcategoryName());

				List<QuestionAndAnswerDetails> questionList = DataAceesLayer.getQADetailsForFordwording(subid);

				logger.debug("word in all question and answer ==> ");
				for (QuestionAndAnswerDetails quesandAnswer : questionList) {
					// split question categories wise
					String[] subcategoriesWords;
					String[] subcategoriesWords1;

					subcategoriesWords = quesandAnswer.getQuestion().getQuestion().split(" ");
					List<Answers> ansList = quesandAnswer.getAnswerList();
					for (Answers ans : ansList) {
						subcategoriesWords1 = ans.getAnswer().split(" ");

						for (String word : subcategoriesWords1) {

							if (remainingQuestioWords.contains(word.toLowerCase())) {
								count1++;
								logger.debug(count1 + "  " + word);

							}
						}
					}

					logger.debug("sub category length   ==========>" + subcategoriesWords.length);
					for (String word : subcategoriesWords) {
						if (remainingQuestioWords.contains(word.toLowerCase())) {
							count1++;
							logger.debug(count1 + "  " + word);

						}
					}
				}

				submapCount.put(subid, count1);
			}
			mapCount.put(cid, submapCount);
		}

		totalCountMap = totalCountOfCommunity(mapCount);

		return totalCountMap;
	}

	/**
	 * 
	 * @param map
	 *            used for calculating total number of main cids words
	 * @return Map<Integer, Integer> key is cid and value is total count
	 *         words...
	 */
	public static Map<Integer, Integer> totalCountOfCommunity(Map<Integer, Map<Integer, Integer>> map) {
		Map<Integer, Integer> cidmap = new HashMap<Integer, Integer>();
		int total;
		cidmap.clear();
		for (Entry<Integer, Map<Integer, Integer>> main : map.entrySet()) {
			int cid = main.getKey();
			Map<Integer, Integer> submap = main.getValue();
			total = 0;
			for (Entry<Integer, Integer> entry : submap.entrySet()) {
				total = total + entry.getValue();
			}
			// System.out.println(cid+" "+total);
			cidmap.put(cid, total);
		}
		logger.debug("Total submap count=====> " + map);
		logger.debug("Total cid count=====> " + cidmap);
		return cidmap;
	}

	/**
	 * 
	 * @param question
	 *            user enter question
	 * @return List<String> without stop words
	 */
	public static List<String> removeStopwords(String question) {
		List<String> remainingWords = new ArrayList<String>();
		List<String> stopwordList = DataAceesLayer.getStopwords();
		String[] words = question.split(" ");
		for (String questioWord : words) {
			if (!stopwordList.contains(questioWord.toLowerCase())) {
				remainingWords.add(questioWord);
			}
		}
		logger.debug("list of removing stop words ===>" + remainingWords);
		return remainingWords;
	}
/**
 *this is for question ford wording to global and after that it turns to local community that are acts as weak ties 
 * @param map
 * @return fw's cids
 *//*
	public static int getCID(Map<Integer, Integer> map) {
		int temp = 0;
		int cid = 0;
		for (Entry<Integer, Integer> enrty : map.entrySet()) {
			if (temp <= enrty.getValue()) {
				temp = enrty.getValue();
				cid = enrty.getKey();

			}
		}
		return cid;
	}*/
	
	/**
	 * this is for question ford wording to global and after that it turns to
	 * local community that are acts as weak ties
	 * 
	 * @param map
	 * @return fw's cids
	 */
	public static int getCID(Map<Integer, Integer> map) {
		int temp = 0;
		int cid = 0;
		boolean flag = false;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Entry<Integer, Integer> entry1 : map.entrySet()) {
			if (entry1.getValue() != 0) {
				list.add(entry1.getValue());
			}
		}

		System.out.println("size is   " + list.size());
		if (list.size() != 0) {
			for (Entry<Integer, Integer> enrty : map.entrySet()) {

				if (temp <= enrty.getValue()) {
					System.out.println(temp);
					temp = enrty.getValue();
					cid = enrty.getKey();
				}
			}
		} else {
			cid = -1;
		}
		System.out.println(cid);
		return cid;
	}

	
	public static void main(String[] args) {

		PriviousQuestion priviousQuestion = new PriviousQuestion();
		priviousQuestion.setQuestion("class java");
		priviousQuestion.setCid(6);
		fordwordQustion(priviousQuestion);
	}

}
