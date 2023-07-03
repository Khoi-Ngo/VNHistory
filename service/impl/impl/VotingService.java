package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.VoteBlogForm;
import com.swp.vnhistory.dto.request.VoteCommentBlogForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
//import com.swp.vnhistory.dto.respone.ViewBlogVoting;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.model.BlogCommentVoting;
import com.swp.vnhistory.model.BlogVoting;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IBlogCommentRepo;
import com.swp.vnhistory.repository.IBlogCommentVotingRepo;
import com.swp.vnhistory.repository.IBlogVotingRepo;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class VotingService { // chi member voi duoc vote

	@Autowired
	IBlogCommentRepo blogCommentRepo;
	@Autowired
	IBlogCommentVotingRepo blogCommentVotingRepo;
	@Autowired
	IBlogsRepository blogsRepository;
	@Autowired
	IBlogVotingRepo blogVotingRepo;
	@Autowired
	IUserRepository userRepository;

//	@Autowired
//	IBlogCommentRepo blogCommentRepo;

	// voting blog
	public Object voteBlog(VoteBlogForm form) {

		if (form == null) {
			return null;
		}
		if (form.getBlogId() <= 0) {
			return null;
		}
		if (form.getUserId() <= 0) {
			return new ResponeMessage("your account not found");
		}
		// check vote hay chua
//		BlogVoting blogVoting = blogVotingRepo.findByUserIdandBlogid(form.getUserId(),form.getBlogId());
//		BlogVoting blogVoting = blogVotingRepo.findByUserUserIdAndBlogBlogId(form.getUserId(), form.getBlogId());
		List<BlogVoting> listblogvotebyuserid = blogVotingRepo.findByUserId(form.getUserId());
		BlogVoting blogVoting = null;
		if (listblogvotebyuserid == null) {
			// chua vote
		}

		else if (listblogvotebyuserid.isEmpty()) {
			// chua vote
		}

		else {
			for (BlogVoting i : listblogvotebyuserid) {
				if (i.getBlog().getBlogID() == form.getBlogId()) {
					// da vote rui
					blogVoting = i;
					break;
				}
			}
		}
		if (blogVoting == null)// chua vote
		{
			User user = userRepository.getById(form.getUserId());
			Blog blog = blogsRepository.getById(form.getBlogId());

			BlogVoting newBlogVoting = new BlogVoting(user, blog);
			blogVotingRepo.save(newBlogVoting);
			// tang luot vote trong table blog
			blogsRepository.incrementNoOfVoteByOne(form.getBlogId());

			return new ResponeMessage("vote successfully");
		}

		else {
//			// vote roi
//
//			// xoa trong table vote

			blogVotingRepo.delete(blogVoting);
//
//			// giam trong bang table blog
			blogsRepository.decrementNoOfVoteByOne(form.getBlogId());
			return new ResponeMessage("recall voting already");

//				return new ResponeMessage("updating the code");
		}

	}

	// voting comment blog
	public Object voteCommnetBlog(VoteCommentBlogForm form) {
		if (form == null) {
			return null;
		}
		if (form.getUserId() <= 0) {
			return new ResponeMessage("your account not found");
		}
		if (form.getCommentId() <= 0) {
			return null;
		}

		try {
//		BlogCommentVoting blogCommentVoting = blogCommentVotingRepo.findByUser_UserId(form.getUserId());
			BlogComment blogComment = blogCommentRepo.getById(form.getCommentId());
			User user = userRepository.getById(form.getUserId());

			BlogCommentVoting blogCommentVoting = blogCommentVotingRepo.findByUserAndBlogComment(user, blogComment);
			if (blogCommentVoting == null) {
				// chua vote

				BlogCommentVoting newBlogCommentVoting = new BlogCommentVoting(user, blogComment);
				// tang luot vote trong table vote
				blogCommentRepo.increaseVoteByBlogCommentID(form.getCommentId());
				blogCommentVotingRepo.save(newBlogCommentVoting);

				return new ResponeMessage("vote successfully");

			} else {
				// vote roi

				// xoa trong table blog comment voting
				blogCommentVotingRepo.deleteByUserIdAndBlogCommentId(form.getUserId(), form.getCommentId());

				// giam luot vote trong table blog comment
				blogCommentRepo.decreaseVoteByBlogCommentID(form.getCommentId());

				return new ResponeMessage("recall already");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	// view voting blog
	public Object viewVotingBlog(int blogId) {

		Long longBlogId = new Long(blogId);

		if (blogId <= 0 || longBlogId == null) {
			return new ResponeMessage("no blog found");
		}

		List<String> listViewBlogVoting = new ArrayList<>();
		List<BlogVoting> listAllBlogVoting = blogVotingRepo.findAll();
		// check null
		if (listAllBlogVoting == null) {
			return "1";
		}
		if (listAllBlogVoting.isEmpty()) {
			return "2";
		}

		for (BlogVoting i : listAllBlogVoting) {

			if (i.getBlog().getBlogID() == longBlogId) {
				listViewBlogVoting.add(i.getUser().getUsername());
			}
		}

		return listViewBlogVoting;
//		return blogVotingRepo.findAllByBlogId(blogId);
//		return null;
	}

	// view voting commentBlog
	public List<BlogCommentVoting> viewVotingBlogComment(long blogCommentId) {
//		return blogCommentVotingRepo.findAll();
		return null;
	}
}
