package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;

/**
 * Servlet implementation class NewCategoryInsertionController
 */
@WebServlet("/NewCategoryInsertionController")
public class NewCategoryInsertionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewCategoryInsertionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = request.getParameter("category");
		String subcategory = request.getParameter("subcategory");
		String desc=request.getParameter("desc");

		String[] subCategories=subcategory.split(",");
		boolean flag=DataAceesLayer.insertNewCategory(category);
		
		int cid=0;
		boolean flag1 = false;
		boolean  insertDescFlag;
		if(flag==true)
			cid = DataAceesLayer.getMaxCID();
			for(String sub:subCategories)
			{
			 flag1=DataAceesLayer.insertSubCategory(cid,sub);
			}
		 insertDescFlag = DataAceesLayer.insertDesc(cid,desc);
			if(flag1==true)
			response.sendRedirect("InsertCateSucessful.jsp");
	}

}
