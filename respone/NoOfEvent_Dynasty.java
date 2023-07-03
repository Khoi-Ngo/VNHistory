package com.swp.vnhistory.dto.respone;

public class NoOfEvent_Dynasty {

	private String dynastyName;
	private int count;
	private int year;
	private int month;

	private int dynastyId;

	public NoOfEvent_Dynasty(String dynastyName, int count, int year, int month, int dynastyId) {
		super();
		this.dynastyName = dynastyName;
		this.count = count;
		this.year = year;
		this.month = month;
		this.dynastyId = dynastyId;
	}

	public String getDynastyName() {
		return dynastyName;
	}

	public void setDynastyName(String dynastyName) {
		this.dynastyName = dynastyName;
	}

	public NoOfEvent_Dynasty() {
		super();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDynastyId() {
		return dynastyId;
	}

	public void setDynastyId(int dynastyId) {
		this.dynastyId = dynastyId;
	}

}
