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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginController.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Login Start here....");
		String email = request.getParameter("Email");
		String pass = request.getParameter("Password");
		UserDetails u = DataAceesLayer.getEmailPass(email, pass);
		if (u != null) {

			if (email.equals(u.getEmail()) && pass.equals(u.getPass())) {
				HttpSession session = request.getSession();
				logger.debug("login successful goto home.jsp");
				session.setAttribute("uid", u.getId());
				response.sendRedirect("Home.jsp?uid="+u.getId());

			} else {
				logger.debug("login fail goto error.jsp");
				response.sendRedirect("Error.jsp");
			}

		}
	}

}
