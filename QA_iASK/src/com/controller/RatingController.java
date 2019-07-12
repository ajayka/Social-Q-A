package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;
import com.model.Rating;

/**
 * Servlet implementation class RatingController
 */
@WebServlet("/RatingController")
public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rating =Integer.parseInt(request.getParameter("star"));
		int uid=Integer.parseInt(request.getParameter("uid"));
		int aid=Integer.parseInt(request.getParameter("aid"));
		int qid=Integer.parseInt(request.getParameter("qid"));
		
		Rating rate=new Rating();
		
		rate.setAid(aid);
		rate.setQid(qid);
		rate.setRating(rating);
		rate.setUid(uid);
		
		Boolean flag=DataAceesLayer.insertRating(rate);
		
		if(flag==true)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("RatingSucess.jsp");
			dispatcher.forward(request, response);
		}
	}

}
