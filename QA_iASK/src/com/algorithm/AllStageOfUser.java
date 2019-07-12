package com.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.DAO.DataAceesLayer;
import com.model.Answers;
import com.model.Question;

public class AllStageOfUser {
	//here we are finding the best answerer in selected cid 
	public static List<Integer> BestAnswerer(int cid) {
		Map<Question, Double> responsemap = ResponseRate.ckeckResponceRate(cid);
		Map<String, Integer> mutualMap = MutualInteraction.mutualInteraction(cid);
		Map<Question, Double> responseDelay = ReponseDelay.responseDelay(cid);
		@SuppressWarnings("unused")
		int[] qidsa=new int[responsemap.size()+mutualMap.size()+responseDelay.size()];
		List<Integer> qids = new ArrayList<Integer>();
		for (Entry<Question, Double> entry : responsemap.entrySet()) {
			Question question = entry.getKey();
			qids.add(question.getUid());
			
		}
		for (Entry<Question, Double> entry : responseDelay.entrySet()) {
			Question question = entry.getKey();
			qids.add(question.getUid());
		}
		for (Entry<String, Integer> entry : mutualMap.entrySet()) {
			String[] arrqids = entry.getKey().split(",");
			qids.add(Integer.parseInt(arrqids[0]));
		}
		Set<Integer> dublicate=findDuplicates(qids.toArray());
		List<Answers> list=DataAceesLayer.getTopScore(dublicate);
		List<Integer> finalList=new ArrayList<>();
 		for(Integer i:dublicate){
			double temp=0;
			int id = 0;
			for(Answers a:list)
			{
				if(i==a.getUid())
				{
					temp=temp+a.getRating();
					id=a.getUid();
				}
			}
			if(id>0)
			{
				finalList.add(i);
			}
		}
		
		return finalList;
	}

	public static Set<Integer> findDuplicates(Object[] input) {

		Set<Integer> duplicates = new HashSet<Integer>();
		for (int i = 0; i < input.length; i++) {
			for (int j = 1; j < input.length; j++) {
				if (input[i] == input[j] && i != j) {
					duplicates.add((Integer) input[i]);
					break;
				}
			}
		}
		//System.out.println(duplicates);
		return duplicates;
	}

	public static void main(String[] args) {
		BestAnswerer(5);
	}
}
