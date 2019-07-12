package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;
import com.model.Notification;

/**
 * Servlet implementation class NotificationController
 */
@WebServlet("/NotificationController")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notification notification=new Notification();
		notification.setCuid(Integer.parseInt(request.getParameter("cuid")));
		notification.setFuid(Integer.parseInt(request.getParameter("fuid")));
		notification.setPost(request.getParameter("post"));

		boolean flag=DataAceesLayer.insertNotification(notification);
		
		if(flag==true)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("friendList.jsp");
			dispatcher.forward(request, response);
		}
	}

}
