package com.swp.vnhistory.dto.respone;

import java.util.List;

public class HomepageResponse {

	private List<EventForm> listCurMonth;
	private List<EventForm> listOutstanding;
	private List<BlogForm> listBlogHighVoting;
	private List<BlogForm> listBlogCurMonth;
	public HomepageResponse() {
		super();
	}
	public HomepageResponse(List<EventForm> listCurMonth, List<EventForm> listOutstanding,
			List<BlogForm> listBlogHighVoting, List<BlogForm> listBlogCurMonth) {
		super();
		this.listCurMonth = listCurMonth;
		this.listOutstanding = listOutstanding;
		this.listBlogHighVoting = listBlogHighVoting;
		this.listBlogCurMonth = listBlogCurMonth;
	}
	public List<EventForm> getListCurMonth() {
		return listCurMonth;
	}
	public void setListCurMonth(List<EventForm> listCurMonth) {
		this.listCurMonth = listCurMonth;
	}
	public List<EventForm> getListOutstanding() {
		return listOutstanding;
	}
	public void setListOutstanding(List<EventForm> listOutstanding) {
		this.listOutstanding = listOutstanding;
	}
	public List<BlogForm> getListBlogHighVoting() {
		return listBlogHighVoting;
	}
	public void setListBlogHighVoting(List<BlogForm> listBlogHighVoting) {
		this.listBlogHighVoting = listBlogHighVoting;
	}
	public List<BlogForm> getListBlogCurMonth() {
		return listBlogCurMonth;
	}
	public void setListBlogCurMonth(List<BlogForm> listBlogCurMonth) {
		this.listBlogCurMonth = listBlogCurMonth;
	}
}
