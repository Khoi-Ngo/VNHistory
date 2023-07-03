package com.swp.vnhistory.dto.respone;

public class NoOfEnrollForm {

	private long count;
	private int month;
	private long year;
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getYear() {
		return year;
	}
	public NoOfEnrollForm() {
		super();
	}
	public NoOfEnrollForm(long count, int month, long year) {
		super();
		this.count = count;
		this.month = month;
		this.year = year;
	}
	public void setYear(long year) {
		this.year = year;
	}
}
