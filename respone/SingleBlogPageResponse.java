package com.swp.vnhistory.dto.respone;

import java.util.List;

public class SingleBlogPageResponse {

	private BlogForm blogForm;
	private List<BlogCommentForm> listCmtForm;

	public BlogForm getBlogForm() {
		return blogForm;
	}

	public void setBlogForm(BlogForm blogForm) {
		this.blogForm = blogForm;
	}

	public List<BlogCommentForm> getListCmtForm() {
		return listCmtForm;
	}

	public void setListCmtForm(List<BlogCommentForm> listCmtForm) {
		this.listCmtForm = listCmtForm;
	}

	public SingleBlogPageResponse() {
		super();
	}

	public SingleBlogPageResponse(BlogForm blogForm, List<BlogCommentForm> listCmtForm) {
		super();
		this.blogForm = blogForm;
		this.listCmtForm = listCmtForm;
	}

}
