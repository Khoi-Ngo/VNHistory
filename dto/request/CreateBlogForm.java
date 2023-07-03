package com.swp.vnhistory.dto.request;

import java.time.LocalDate;

import com.swp.vnhistory.model.User;

public class CreateBlogForm {

	private String content;
	private long userId;
//	private LocalDate date;
	private String hashtag;

	public CreateBlogForm() {
		// Default constructor
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public CreateBlogForm(String content, long userId) {
		super();
		this.content = content;
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CreateBlogForm(String content, long userId, String hashtag) {
		super();
		this.content = content;
		this.userId = userId;
		this.hashtag = hashtag;
	}

}
