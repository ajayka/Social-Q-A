package com.model;

public class UserCommunityInfomation {

	int cid;
	int subid;
	int uid;
	String subCategoryName;
	String categoryName;
	
	String fname;
	String lname;
	String email;
	
	@Override
	public String toString() {
		return "UserCommunityInfomation [cid=" + cid + ", subid=" + subid + ", uid=" + uid + ", subCategoryName="
				+ subCategoryName + ", categoryName=" + categoryName + ", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
