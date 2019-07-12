package com.model;

import java.sql.Date;;

public class Answers {

	private int aid;
	private int uid;
	private int qid;
	private String answer;
	private double rating;
	private Date date;
	private int bal;
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(Double double1) {
		this.rating = double1;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Answers [aid=" + aid + ", uid=" + uid + ", qid=" + qid + ", answer=" + answer + ", rating=" + rating
				+ ", date=" + date + "]";
	}
	
}
