package com.swp.vnhistory.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.CreateBlogForm;
import com.swp.vnhistory.dto.request.CreateEventForm;
import com.swp.vnhistory.dto.request.SearchBlogForm;
import com.swp.vnhistory.dto.request.UpdateBlogForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IBlogCommentRepo;
import com.swp.vnhistory.repository.IBlogCommentVotingRepo;
//import com.swp.vnhistory.repository.IBlogVotingRepo;
import com.swp.vnhistory.repository.IBlogsRepository;

@Service
public class BlogServiceImpl {

	@Autowired
	IAccountRepository accountRepository;
	@Autowired
	IBlogsRepository blogsRepository;
	@Autowired
	IBlogCommentVotingRepo blogCommentVotingRepo;
//	@Autowired
//	IBlogVotingRepo blogVotingRepo;
	@Autowired
	IBlogCommentRepo blogCommentRepo;

	// create
	public Object createBlog(CreateBlogForm form) {
		if (form != null) {
//User user, @NotBlank String content, LocalDate dateBlogCreated, int noOfView, int noOfVote
			LocalDate date = LocalDate.now();
			User user = accountRepository.getById(form.getUserId());

			Blog blog = new Blog(user, form.getContent(), form.getHashtag(), date);
			blogsRepository.save(blog);
			return new ResponeMessage("created blog");

		} else {
			return new ResponeMessage("form invalid");
		}

	}

	public String getFirst15Words(String input) {
		// Remove leading and trailing whitespace
		input = input.trim();

		// Split the string into an array of words
		String[] words = input.split("\\s+");

		// Initialize variables
		StringBuilder sb = new StringBuilder();
		int wordCount = 0;
		int maxWords = 15;

		// Concatenate words until the substring contains approximately 50 words
		for (String word : words) {
			sb.append(word).append(" ");
			wordCount++;

			if (wordCount >= maxWords) {
				break;
			}
		}

		// Remove trailing whitespace and return the substring
		return sb.toString().trim();
	}

	// delete
	public ResponeMessage deleteBlog(long blogId) {

		if (blogId <= 0) {
			return new ResponeMessage("Invalid blogId");
		} else {

			// delete table blog voting
//			blogVotingRepo.deleteByBlogBlogId((int) blogId); => tai sao ???

			// delete blog comment voting
			blogCommentVotingRepo.deleteByBlogComment_BlogCommentID(blogId);
			// delete table comment
			blogCommentRepo.deleteByBlogBlogID(blogId);
			// delete table blog
			blogsRepository.deleteByBlogID(blogId);
			return new ResponeMessage("Deleted blog");
		}
	}

	// preview
	public UpdateBlogForm previewBlog(long blogId) {
		Blog blog = blogsRepository.getById(blogId);
		UpdateBlogForm form = new UpdateBlogForm();
		form.setBlogId(blogId);
		form.setContent(blog.getContent());
		return form;
	}
	// update

	public ResponeMessage update(UpdateBlogForm form) {
		if (form == null) {
			return new ResponeMessage("FORM INVALID");
		} else {
			if (form.getContent() == null) {
				form.setContent("");
			}
			if (blogsRepository.updateContentByBlogID(form.getBlogId(), form.getContent()) > 0) {
				return new ResponeMessage("UPDATED BLOG");
			}

		}
		return new ResponeMessage("INVALID");
	}

	// search
	public List<SearchBlogForm> search(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return blogsRepository.findBlogsByContentContainingKeyword(keyword);
	}

	public List<Blog> searchTag(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return blogsRepository.findBlogsByHashtagContaining(keyword);
	}

	// preview createblog form
	public CreateBlogForm previewCreate(CreateBlogForm form) {
		return form;
	}
}
