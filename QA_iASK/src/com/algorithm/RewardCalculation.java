package com.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.DAO.DataAceesLayer;
import com.model.Reward;

public class RewardCalculation {

	public RewardCalculation(int cid) {
		List<Reward> rewardList = DataAceesLayer.getAllSelectdReward(cid);
		Set<Integer> uids = new HashSet<Integer>();
		for (Reward reward : rewardList) {
			uids.add(reward.getUid());
		}
		for (Integer uid : uids) {
			int total = DataAceesLayer.calculateTotalRewars(uid);
			int bal = DataAceesLayer.getReward(uid);
			if (bal == 0)
				DataAceesLayer.insertTotalReward(uid, total);
			else {
				bal += total;
				DataAceesLayer.updateReward(uid, bal);
			}
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		RewardCalculation rewardCalculation = new RewardCalculation(5);
	}

}
