package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;

/**
 * Servlet implementation class ApproveController
 */
@WebServlet("/ApproveController")
public class ApproveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("selected id is  ===> "+request.getParameter("id"));
		if(request.getParameter("submit").equalsIgnoreCase("Approve"))
		{
			DataAceesLayer.updateSuggestion(request.getParameter("id"),"CQA admin approve your category");
			response.sendRedirect("AdminHome.jsp");
		}else
		{
			DataAceesLayer.updateSuggestion(request.getParameter("id"),"CQA admin not approve your category");
			response.sendRedirect("AdminHome.jsp");
		}
	}

}
