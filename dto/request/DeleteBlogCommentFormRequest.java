package com.swp.vnhistory.dto.request;

public class DeleteBlogCommentFormRequest {

	private long blogCommentId;
	private long userId;

	public long getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(long blogCommentId) {
		this.blogCommentId = blogCommentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
