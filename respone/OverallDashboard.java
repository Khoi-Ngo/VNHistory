package com.swp.vnhistory.dto.respone;

public class OverallDashboard {

	private long noOfDoquiz;
	private long noOfEnroll;
	private long noOfBlog;
	private long noOfEvent;
	private int noOfAcc;
	public long getNoOfDoquiz() {
		return noOfDoquiz;
	}
	public void setNoOfDoquiz(long noOfDoquiz) {
		this.noOfDoquiz = noOfDoquiz;
	}
	public long getNoOfEnroll() {
		return noOfEnroll;
	}
	public void setNoOfEnroll(long noOfEnroll) {
		this.noOfEnroll = noOfEnroll;
	}
	public long getNoOfBlog() {
		return noOfBlog;
	}
	public void setNoOfBlog(long noOfBlog) {
		this.noOfBlog = noOfBlog;
	}
	public long getNoOfEvent() {
		return noOfEvent;
	}
	public void setNoOfEvent(long noOfEvent) {
		this.noOfEvent = noOfEvent;
	}
	public int getNoOfAcc() {
		return noOfAcc;
	}
	public void setNoOfAcc(int noOfAcc) {
		this.noOfAcc = noOfAcc;
	}
	public OverallDashboard(long noOfDoquiz, long noOfEnroll, long noOfBlog, long noOfEvent, int noOfAcc) {
		super();
		this.noOfDoquiz = noOfDoquiz;
		this.noOfEnroll = noOfEnroll;
		this.noOfBlog = noOfBlog;
		this.noOfEvent = noOfEvent;
		this.noOfAcc = noOfAcc;
	}
	public OverallDashboard(long noOfDoquiz, long noOfEnroll, long noOfBlog, long noOfEvent) {
		super();
		this.noOfDoquiz = noOfDoquiz;
		this.noOfEnroll = noOfEnroll;
		this.noOfBlog = noOfBlog;
		this.noOfEvent = noOfEvent;
	}



}
