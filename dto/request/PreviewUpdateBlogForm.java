package com.swp.vnhistory.dto.request;

public class PreviewUpdateBlogForm {

	private long blogId;

	public long getBlogId() {
		return blogId;
	}

	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}

	public PreviewUpdateBlogForm(long blogId) {
		super();
		this.blogId = blogId;
	}

	public PreviewUpdateBlogForm() {
		super();
	}
	
}
