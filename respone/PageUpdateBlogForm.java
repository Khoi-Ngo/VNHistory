package com.swp.vnhistory.dto.respone;

import javax.persistence.Lob;

public class PageUpdateBlogForm {

	private long blogId;
	@Lob
	private String content;
	
	private String hashtag;
	
	public PageUpdateBlogForm() {
		super();
	}

	private String userName;

	public PageUpdateBlogForm(long blogId, String content, String hashtag, String userName) {
		super();
		this.blogId = blogId;
		this.content = content;
		this.hashtag = hashtag;
		this.userName = userName;
	}

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

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
