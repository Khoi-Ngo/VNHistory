package com.swp.vnhistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.UpdateBlogCommentForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.repository.IBlogCommentRepo;

@Service
public class BlogCommentService {

	@Autowired
	IBlogCommentRepo blogCommentRepo;

	// delete
	public ResponeMessage delete(long blogCmtId) {
		if (blogCmtId <= 0) {
			return new ResponeMessage("invalid blog_comment_id");
		} else {
			blogCommentRepo.deleteByBlogCommentID(blogCmtId);
			return new ResponeMessage("DELETED BLOG COMMENT");
		}
	}
	// create

	// update
	public BlogComment update(UpdateBlogCommentForm form) {
		if (form == null) {
			return null;
		} else {
			if (form.getContent() == null || form.getContent().equals("")) {
				BlogComment blogComment = blogCommentRepo.getById(form.getBlogCommentId());
				form.setContent(blogComment.getComment());
			}
			blogCommentRepo.updateCommentByBlogCommentID(form.getBlogCommentId(), form.getContent());
			return blogCommentRepo.getById(form.getBlogCommentId());
		}
	}

}
