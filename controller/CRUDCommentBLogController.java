package com.swp.vnhistory.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.CommentBlogFormRequest;
//import com.swp.vnhistory.dto.request.DeleteBlogCommentForm;
import com.swp.vnhistory.dto.request.DeleteBlogCommentFormRequest;
import com.swp.vnhistory.dto.request.UpdateBlogCommentForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IBlogCommentRepo;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.repository.IUserRepository;
import com.swp.vnhistory.service.impl.BlogCommentService;

@RestController // all function occured in single Page
//blog thi co them voting
@RequestMapping("/blog")
public class CRUDCommentBLogController { // member co quen CRUD, editor co quyen DR

	@Autowired
	BlogCommentService blogCommentService;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IBlogsRepository blogsRepository;

	@Autowired
	IBlogCommentRepo blogCommentRepo;

	// craete || write form and post
	@PostMapping("/comment")
	@ResponseBody
	public ResponseEntity<?> postComment(@Valid @RequestBody CommentBlogFormRequest form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Comment is empty"));
		} else {
			// call service
			try {
				User user = userRepository.getById(form.getUserId());
				Blog blog = blogsRepository.getById(form.getBlogId());

				if (user == null || blog == null) {
					return ResponseEntity.notFound().build();
//				return ResponseEntity.ok("1");
				}

				if (form.getContent() == null) {
					return ResponseEntity.ok(new ResponeMessage("empty content"));
				}

				BlogComment blogComment = new BlogComment(user, blog, form.getContent(), LocalDate.now(), 0);
				blogCommentRepo.save(blogComment);
				return ResponseEntity.ok(new ResponeMessage("Done"));

			} catch (Exception e) {
//			return ResponseEntity.ok("2");
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}

		}
	}

	@DeleteMapping("/comment") // for both member and editor
	@ResponseBody
	public ResponseEntity<?> deleteComment(@Valid @RequestBody DeleteBlogCommentFormRequest form) {
		if (form == null) {
			// invalid
			return ResponseEntity.ok(new ResponeMessage("Cannot delete comment not found"));
		} else {
			try {
				BlogComment blogComment = blogCommentRepo.getById(form.getBlogCommentId());
				if (blogComment == null || blogComment.getUser().getUserId() != form.getUserId()) {
					return ResponseEntity.notFound().build();
				}
				// check co phai cua user do ko

				/// query update

				return ResponseEntity.ok(blogCommentService.delete(form.getBlogCommentId()));

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong"));
				// TODO: handle exception
			}
		}

	}

	@PutMapping("/comment")
	@ResponseBody
	public ResponseEntity<?> updateComment(@Valid @RequestBody UpdateBlogCommentForm form) {
		if (form == null) {
			// invalid
			return ResponseEntity.ok(new ResponeMessage("Cannot update comment not found"));
		} else {
			try {
				BlogComment blogComment = blogCommentRepo.getById(form.getBlogCommentId());
				if (blogComment == null || blogComment.getUser().getUserId() != form.getUserId()) {
					return ResponseEntity.notFound().build();
				}
				// check co phai cua user do ko

				/// query update
				blogCommentRepo.updateCommentByBlogCommentID(form.getBlogCommentId(), form.getContent());
				return ResponseEntity.ok(new ResponeMessage("Updated"));

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong"));
				// TODO: handle exception
			}
		}
	}

}
