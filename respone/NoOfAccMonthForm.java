package com.swp.vnhistory.dto.respone;

import com.swp.vnhistory.model.User;

public class NoOfAccMonthForm {

	private int noOfUsers;
	private int month;
	private int year;
	public int getNoOfUsers() {
		return noOfUsers;
	}
	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public NoOfAccMonthForm(int noOfUsers, int month, int year) {
		super();
		this.noOfUsers = noOfUsers;
		this.month = month;
		this.year = year;
	}
	
	
}
