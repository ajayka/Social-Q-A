package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.DAO.DataAceesLayer;
import com.model.UserDetails;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
		String email = request.getParameter("Email");
		String pass = request.getParameter("Password");
		logger.debug("AdminController Starts here..");
		UserDetails u = DataAceesLayer.getEmailPassForAdmin(email, pass);
		if (u != null) {

			if (email.equals(u.getEmail()) && pass.equals(u.getPass())) {
				HttpSession session = request.getSession();
				session.setAttribute("AdminID", u.getId());
				logger.debug("AdminController Admin sucessful");
				if(DataAceesLayer.getSuggestCategoryCount()==5)
				{
					DataAceesLayer.truncateSuggestCategory();
				}
				
				response.sendRedirect("AdminHome.jsp?adminID="+u.getId());

			} else {
				response.sendRedirect("Error.jsp");
			}

		}
	}

}
