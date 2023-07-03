package com.swp.vnhistory.controller.function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.VoteBlogForm;
import com.swp.vnhistory.dto.request.VoteCommentBlogForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.service.impl.VotingService;

@RestController
public class Voting {

	@Autowired
	VotingService votingService;

	@PostMapping("/voteBlog")
	@ResponseBody
	public ResponseEntity<?> voteBlog(@Valid @RequestBody VoteBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("failed to vote"));
		}
//		form.setBlogId(4);
//		form.setUserId(1);
		if (form.getBlogId() <= 0) {
			return ResponseEntity.ok(new ResponeMessage("no blog found"));
		}
		// else -> call service
		return ResponseEntity.ok(votingService.voteBlog(form));
//		return null;
	}

	@PostMapping("/voteCommentBlog")
	@ResponseBody
	public ResponseEntity<?> voteCommentBlog(@Valid @RequestBody VoteCommentBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("failed to vote"));
		} else if (form.getCommentId() <= 0) {
			return ResponseEntity.ok(new ResponeMessage("no comment found"));
		} else {

			try {
				return ResponseEntity.ok(votingService.voteCommnetBlog(form));
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong"));
				// TODO: handle exception
			}

		}
	}

	@GetMapping("/viewVoteBlog/{blogId}")
	@ResponseBody
	public ResponseEntity<?> viewVoteBlog(@PathVariable("blogId") int blogId) {
		if (blogId <= 0) {
			return ResponseEntity.ok(new ResponeMessage("no blog found"));
		}
		return ResponseEntity.ok(votingService.viewVotingBlog(blogId));
	}

	@GetMapping("/viewVoteCommentBlog/{commentId}")
	@ResponseBody
	public ResponseEntity<?> viewVoteCommentBlog(@PathVariable("commentId") int commentId) {
		if (commentId <= 0) {
			return ResponseEntity.ok(new ResponeMessage("no blog comment found"));
		}
		return ResponseEntity.ok(votingService.viewVotingBlogComment(commentId));
	}

}
