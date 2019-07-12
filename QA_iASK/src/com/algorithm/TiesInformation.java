package com.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.DAO.DataAceesLayer;
import com.model.Category;
import com.model.Question;
import com.model.Subcategory;

public class TiesInformation {

	@SuppressWarnings("unused")
	public static boolean checkTies(Question question,Category category,Subcategory subcategory)
	{
		boolean flag=false;
		String ques=question.getQuestion();
		int qid=question.getQid();
		List<Category> categories=DataAceesLayer.getAllCategory();
		List<List<Subcategory>> sublist=new ArrayList<List<Subcategory>>();
		
		for(Category category2:categories){
			List<Subcategory> list=DataAceesLayer.getAllSubCategory(category2.getCid());
			sublist.add(list);
		}
		
		
		return flag;
		
	}
}
