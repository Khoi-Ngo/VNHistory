package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

public class BlogCommentForm {

	private long blogCommentId;
	private String content;
	private LocalDate date;
	private int noOfVote;
	private long userId;
	private String userName;

	public long getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(long blogCommentId) {
		this.blogCommentId = blogCommentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNoOfVote() {
		return noOfVote;
	}

	public void setNoOfVote(int noOfVote) {
		this.noOfVote = noOfVote;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BlogCommentForm() {
		super();
	}

	public BlogCommentForm(long blogCommentId, String content, LocalDate date, int noOfVote, long userId,
			String userName) {
		super();
		this.blogCommentId = blogCommentId;
		this.content = content;
		this.date = date;
		this.noOfVote = noOfVote;
		this.userId = userId;
		this.userName = userName;
	}

}
