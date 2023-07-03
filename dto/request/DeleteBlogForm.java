package com.swp.vnhistory.dto.request;

public class DeleteBlogForm {

	private long BlogId;

	public long getBlogId() {
		return BlogId;
	}

	public void setBlogId(long blogId) {
		BlogId = blogId;
	}

	public DeleteBlogForm(long blogId) {
		super();
		BlogId = blogId;
	}

	public DeleteBlogForm() {
		super();

	}

}
