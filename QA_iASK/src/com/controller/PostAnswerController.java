package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;
import com.fuzzySpell.SpellCheckerApplication;
import com.model.Answers;
import com.model.Rating;

/**
 * Servlet implementation class PostAnswerController
 */
@WebServlet("/PostAnswerController")
public class PostAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answer1=request.getParameter("answer");
		String qid1=request.getParameter("qid");
		String uid1=request.getParameter("uid");
		
		int uid=Integer.parseInt(uid1);
		int qid=Integer.parseInt(qid1);
		
		Answers answer=new Answers();
		Rating rate=new Rating();
		
		answer.setQid(qid);
		answer.setUid(uid);
		String ans=SpellCheckerApplication.spellCheck(answer1.toLowerCase().trim());
		answer.setAnswer(ans);
		Boolean flag=DataAceesLayer.insertAnswer(answer);
		
		rate.setQid(qid);
		rate.setUid(uid);
		rate.setAid(DataAceesLayer.getMaxAId());
		rate.setRating(0);
		
		if(flag==true)
		{
			boolean flag1=DataAceesLayer.insertRating(rate);
			if(flag1==true){
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("asnwerSucess.jsp");
			requestDispatcher.forward(request, response);
			}
		}
	}
}
