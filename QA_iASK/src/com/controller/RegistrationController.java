package com.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DataAceesLayer;
import com.Utility.SendMailBySite;
import com.model.UserDetails;

/**
 * Servlet implementation class veri12
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String fname = request.getParameter("FirstName");
		String lname = request.getParameter("LastName");
		String email = request.getParameter("Email");
		String address = request.getParameter("address");
		String pass = request.getParameter("Password");
		String phnno = request.getParameter("PhoneNumber");
//		System.out.println("im in veri12");

		Random r = new Random();
		int max = 999999, min = 100000;
		int code = r.nextInt((max - min) + max);
		// Database_con.addCode(code);

		try {

			SendMailBySite.sendEmail("smtp.gmail.com", "587", "project.varification@gmail.com", "Email@123", email, code);
			// Database_con.insertSecCode(code);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//response.sendRedirect("Error.jsp");
			e.printStackTrace();
		}

		UserDetails usr = new UserDetails();
		usr.setFname(fname);
		usr.setLname(lname);
		usr.setEmail(email);
		usr.setPass(pass);
		usr.setCode(code);
		usr.setPhnno(phnno);
		usr.setAddress(address);
		
		HttpSession session=request.getSession();
		session.setAttribute("usr", usr);
		
		response.sendRedirect("Verify.jsp");
		
		
	}

}
