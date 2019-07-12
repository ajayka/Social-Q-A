package com.model;

import java.util.List;



public class QuestionAndAnswerDetails {
//	private static final Logger logger = Logger.getLogger(QuestionAndAnswerDetails.class);
	private UserDetails user;
	private Question question;
	private Answers answer;
	private List<Answers> answerList;
	
	
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Answers getAnswer() {
		return answer;
	}
	public void setAnswer(Answers answer) {
		this.answer = answer;
	}
	public List<Answers> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<Answers> answerList) {
		this.answerList = answerList;
	}
/*	@Override
	public String toString() {
		logger.debug("QuestionAndAnswerDetails [user=" + user + ", question=" + question + ", answer=" + answer + "]");
		return "QuestionAndAnswerDetails class";
	}*/
	
}
