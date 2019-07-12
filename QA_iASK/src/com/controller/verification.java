package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DataAceesLayer;
import com.model.UserDetails;


@WebServlet("/verification")
public class verification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("im in verification.java");
		String vcode = request.getParameter("vrcode");

		HttpSession session=request.getSession();
		UserDetails user=(UserDetails) session.getAttribute("usr");
		
		int vrcode = Integer.parseInt(vcode);
		
		// int id = Database_con.getMaxCode();
		// UserDetails u = Database_con.getDetails(id);

		if (vrcode == user.getCode()) {

			int i = DataAceesLayer.addUser(user);
			if (i == 0) {
				response.sendRedirect("InsertFail.jsp");

			} else {
				response.sendRedirect("selectCategory.jsp");
			}

		} else {
			response.sendRedirect("Verify.jsp");
		}
	}

}
