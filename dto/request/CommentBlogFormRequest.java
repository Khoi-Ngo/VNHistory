package com.swp.vnhistory.dto.request;

public class CommentBlogFormRequest {

	private String content;
	private long blogId;
	private long userId;

	public long getUserId() {
		return userId;
	}

	public CommentBlogFormRequest() {
		super();
	}

	public CommentBlogFormRequest(long blogId, long userId) {
		super();
		this.blogId = blogId;
		this.userId = userId;
		this.content = null;
	}

	public CommentBlogFormRequest(String content, long blogId, long userId) {
		super();
		this.content = content;
		this.blogId = blogId;
		this.userId = userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

}
