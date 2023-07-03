package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

public class BlogForm {
	
	private long blogId;
	private String content;
	private LocalDate date;
	private String hashtag;
	private UserForm user;
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
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
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public UserForm getUser() {
		return user;
	}
	public void setUser(UserForm user) {
		this.user = user;
	}
	public BlogForm(long blogId, String content, LocalDate date, String hashtag, UserForm user) {
		super();
		this.blogId = blogId;
		this.content = content;
		this.date = date;
		this.hashtag = hashtag;
		this.user = user;
	}
	public BlogForm() {
		super();
	}
	

	
}
