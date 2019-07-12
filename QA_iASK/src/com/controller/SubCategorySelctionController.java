package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;

/**
 * Servlet implementation class SubCategorySelctionController
 */
@WebServlet("/SubCategorySelctionController")
public class SubCategorySelctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String subid=request.getParameter("subcategoryName");
		String cid=request.getParameter("cid");
		String uid=request.getParameter("uid");
		
		System.out.println(uid);
		boolean flag=DataAceesLayer.insertCommunityInfo(uid, cid, subid);
		
		if(flag==true)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
