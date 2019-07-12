package com.model;

import java.sql.Date;

public class Question {

	private int cid;
	private int uid;
	private int subid;
	private int qid;
	private String question;
	private Date date;
	
	/*@Override
	public String toString() {
		return "Question [cid=" + cid + ", uid=" + uid + ", subid=" + subid + ", qid=" + qid + ", question=" + question
				+ ", date=" + date + "]";
	}*/
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
