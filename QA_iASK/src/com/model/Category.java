package com.model;

public class Category {

	int cid;
	String categoryName;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", categoryName=" + categoryName + ", getCid()=" + getCid()
				+ ", getCategoryName()=" + getCategoryName() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
