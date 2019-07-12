package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.DAO.DataAceesLayer;
import com.algorithm.FineGrainedReputation;
import com.algorithm.QuestionForworded;
import com.fuzzySpell.SpellCheckerApplication;
import com.model.Answers;
import com.model.PriviousQuestion;
import com.model.Question;
import com.model.QuestionAndAnswerDetails;

/**
 * Servlet implementation class SearchQuestionController
 */
@WebServlet("/SearchQuestionController")
public class SearchQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SearchQuestionController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		System.out.println("size before clearing : "+map.size());
		map.clear();
		System.out.println("size after clearing : "+map.size());
		if (map.isEmpty()) {

			String question1 = request.getParameter("question");
			String cid = request.getParameter("cid");
			String uid = request.getParameter("uid");
			String subid = request.getParameter("subid");
			
			//Fuzzy spell check API
			String question = SpellCheckerApplication.spellCheck(question1.toLowerCase());
			PriviousQuestion priviousQuestion = new PriviousQuestion();
			priviousQuestion.setQuestion(question);
			priviousQuestion.setCid(Integer.parseInt(cid));
			
			/**
			 * insert question that are ask by user in previoussearch table in db
			 */
			boolean flag = DataAceesLayer.insertPreviousQuestion(priviousQuestion);
			logger.debug("flag value of search question unsertion  " + flag);
			
			
			if (flag == true) {
				logger.debug("question insertion sucessful in previous question");
				
				/**
				 * Exact match with db question
				 */
				List<QuestionAndAnswerDetails> answerDetails=DataAceesLayer.getQADetails();
				
				List<QuestionAndAnswerDetails> exactMatchList=new ArrayList<QuestionAndAnswerDetails>();
				for(QuestionAndAnswerDetails details:answerDetails)
				{
					Question question2=details.getQuestion();
					if(question2.getQuestion().equalsIgnoreCase(question))
					{
						QuestionAndAnswerDetails andAnswerDetails=new QuestionAndAnswerDetails();
						Question question3=new Question();
						question3.setUid(question2.getUid());
						question3.setCid(question2.getCid());
						question3.setQid(question2.getQid());
						question3.setQuestion(question2.getQuestion());
						andAnswerDetails.setQuestion(question3);
						andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(question2.getQid()));
						exactMatchList.add(andAnswerDetails);
					}
				}
				System.out.println("exact match list===> "+exactMatchList.size());
				if(exactMatchList.size()>0){
				List<QuestionAndAnswerDetails> questionList=DataAceesLayer.searchByWholeQuestion(question);
				exactMatchList.addAll(questionList);
				}
				
				
				
				
				map = QuestionForworded.fordwordQustion(priviousQuestion);
				logger.debug("Map in search Contoller  ==> " + map);
				int fwscid=QuestionForworded.getCID(map);
				
				List<QuestionAndAnswerDetails> relatedMathchList=FineGrainedReputation.relatedQuestion(question,fwscid);
				
				
				HttpSession session = request.getSession();
				
				if(fwscid!=-1){
				//exact match question
				session.setAttribute("exactMatchList", exactMatchList);
				//all related question 
				session.setAttribute("relatedMathchList", relatedMathchList);
				//map for question fw algorithm
				
				session.setAttribute("map", map);
				response.sendRedirect("seachesQuestion.jsp");
				}else{
					
					response.sendRedirect("notFound.jsp");
				}
			} else {
				logger.debug("question insertion failed in previous question");
			}

		}
	}

}
