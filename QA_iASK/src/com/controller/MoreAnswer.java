package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DataAceesLayer;
import com.Utility.SendMailBySite;
import com.Utility.ServerInfo;
import com.model.Question;
import com.model.UserCommunityInfomation;

/**
 * Servlet implementation class MoreAnswer
 */
@WebServlet("/MoreAnswer")
public class MoreAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int qid = Integer.parseInt(request.getParameter("qid"));

		Question question = DataAceesLayer.getQuestion(qid);

		List<UserCommunityInfomation> communityInfomations = DataAceesLayer
				.getAllUserCommunityInfomation(question.getCid());
		Integer port = ServerInfo.getTomcatPortFromConfigXml();
		String ipAdd = ServerInfo.systemIP();
		String URL = "http://" + ipAdd + ":" + port + "/QA_iASK/Login.jsp";
		String[] recipientList;
		String email = "";
		for (UserCommunityInfomation community : communityInfomations) {
			if (question.getUid() != community.getUid()) {
				try {
					email = community.getEmail() + "," + email;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		recipientList = email.split(",");

		try {
			SendMailBySite.sendEmailToFriend1("smtp.gmail.com", "587", "project.varification@gmail.com", "Email@123",
					recipientList, question.getQuestion(), URL);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('We again notified to other user');");
			out.println("location='RecenltyQuestion.jsp';");
			out.println("</script>"); 
	}

	

}
