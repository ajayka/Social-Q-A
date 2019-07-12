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
import com.model.UserCommunityInfomation;

public class MutualInteraction {
	public static boolean ASC = true;
	public static boolean DESC = false;
	private static final Logger logger = Logger.getLogger(MutualInteraction.class);

	public static Map<String, Integer> mutualInteraction(int cid) {
		Map<String, Integer> mutaualInteractionCount = new HashMap<String, Integer>();
		Map<String, Integer> maxMap = new HashMap<String, Integer>();
		List<UserCommunityInfomation> allCommunityInfomation = DataAceesLayer.getAllUserCommunityInfomation(cid);

//		System.out.println(allCommunityInfomation.size());
		for (UserCommunityInfomation communityInfomation : allCommunityInfomation) {
			for (UserCommunityInfomation communityInfomation2 : allCommunityInfomation) {
				if (communityInfomation.getUid() != communityInfomation2.getUid()) {
//					System.out.println();
					int count = DataAceesLayer.getCommunicationCount(communityInfomation.getUid(),
							communityInfomation2.getUid());
					String ids = communityInfomation.getUid() + "," + communityInfomation2.getUid();
					mutaualInteractionCount.put(ids, count);
				}
			}

		}
		logger.debug("Mutual interaction map ====> " + mutaualInteractionCount);
		logger.debug("Mutual interaction map size ====> " + mutaualInteractionCount.size());

		Map<String, Integer> sortedMapAsc = sortByComparator(mutaualInteractionCount, DESC);

//		System.out.println(sortedMapAsc);
		int count = 1;
		for (Entry<String, Integer> entry : sortedMapAsc.entrySet()) {
			if (count < 10) {
				maxMap.put(entry.getKey(), entry.getValue());
			} else {
				break;
			}
			count++;

		}
		logger.debug("Final Mutual interaction map  ====> " + maxMap);
//		System.out.println(maxMap);
		return maxMap;
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

/*	public static void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}*/

	public static void main(String[] args) {
		// ckeckResponceRate(6);
//		Map<String, Integer> map =mutualInteraction(6);
		

	}
}
