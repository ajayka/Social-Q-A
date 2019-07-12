package com.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DataAceesLayer;
import com.Utility.SendMailBySite;
import com.Utility.ServerInfo;
import com.fuzzySpell.SpellCheckerApplication;
import com.model.Question;
import com.model.UserCommunityInfomation;

/**
 * Servlet implementation class PostQuestionController
 */
@WebServlet("/PostQuestionController")
public class PostQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String quetion1 = request.getParameter("question");
		String cid1 = request.getParameter("cid");
		String uid1 = request.getParameter("uid");
		String subid1 = request.getParameter("subid");

		int subid = Integer.parseInt(subid1);
		int uid = Integer.parseInt(uid1);
		int cid = Integer.parseInt(cid1);

		Question question = new Question();
		question.setCid(cid);

		question.setSubid(subid);
		question.setUid(uid);
		// here we are checking the spell
		String ques = SpellCheckerApplication.spellCheck(quetion1.toLowerCase().trim());
		question.setQuestion(ques);

		Question checkQuestion = DataAceesLayer.checkQuestionExist(question);
		System.out.println(checkQuestion.getQid()+" and "+checkQuestion.getQuestion());
		
		if (checkQuestion.getQid() == 0) {
			Boolean flag = DataAceesLayer.insertQuestion(question);
			if (flag == true) {
				List<UserCommunityInfomation> communityInfomations = DataAceesLayer.getAllUserCommunityInfomation(cid);
				Integer port = ServerInfo.getTomcatPortFromConfigXml();
				String ipAdd = ServerInfo.systemIP();
				String URL = "http://" + ipAdd + ":" + port + "/QA_iASK/Login.jsp";
				String[] recipientList;
				String email = "";
				for (UserCommunityInfomation community : communityInfomations) {
					if (uid != community.getUid()) {
						try {
							email = community.getEmail() + "," + email;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

				recipientList = email.split(",");

				try {
					SendMailBySite.sendEmailToFriend("smtp.gmail.com", "587", "project.varification@gmail.com",
							"Email@123", recipientList, ques, URL);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String msg = "Your posted question found unique on CQA system.";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("mailSend.jsp?msg=" + msg);
				requestDispatcher.forward(request, response);
			}
		} else {
			Boolean flag = DataAceesLayer.updateQuestionDate(checkQuestion);

			if (flag == true) {
				List<UserCommunityInfomation> communityInfomations = DataAceesLayer.getAllUserCommunityInfomation(cid);
				Integer port = ServerInfo.getTomcatPortFromConfigXml();
				String ipAdd = ServerInfo.systemIP();
				String URL = "http://" + ipAdd + ":" + port + "/QA_iASK/Login.jsp";
				String[] recipientList;
				String email = "";
				for (UserCommunityInfomation community : communityInfomations) {
					if (uid != community.getUid()) {
						try {
							email = community.getEmail() + "," + email;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				recipientList = email.split(",");
				try {
					SendMailBySite.sendEmailToFriendAgain("smtp.gmail.com", "587", "project.varification@gmail.com",
							"Email@123", recipientList, ques, URL);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String msg = "Already Question avalible in CQA system, You want more answers so we notified to community users";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("mailSend.jsp?msg=" + msg);
				requestDispatcher.forward(request, response);
			}
		}
	}

}
