package com.model;

public class UserDetails {

	public UserDetails() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserDetails [email=" + email + ", pass=" + pass + ", fname=" + fname + ", phnno=" + phnno + ", lname="
				+ lname + ", con_pass=" + con_pass + ", code=" + code + ", id=" + id + "]";
	}

	private String email, pass, fname, phnno, lname, con_pass,address;
	public String getPhnno() {
		return phnno;
	}

	public void setPhnno(String phnno) {
		this.phnno = phnno;
	}

	private int code, id;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCon_pass() {
		return con_pass;
	}

	public void setCon_pass(String con_pass) {
		this.con_pass = con_pass;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
