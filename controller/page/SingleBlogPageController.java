package com.swp.vnhistory.controller.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.BlogCommentForm;
import com.swp.vnhistory.dto.respone.BlogForm;
import com.swp.vnhistory.dto.respone.SingleBlogPageResponse;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.repository.IBlogCommentRepo;
import com.swp.vnhistory.repository.IBlogsRepository;

@RestController
@RequestMapping("/singleblog")
public class SingleBlogPageController {

	@Autowired
	IBlogsRepository blogsRepository;

	@Autowired
	IBlogCommentRepo blogCommentRepo;

	@GetMapping("/{blogId}")
	@ResponseBody
	public ResponseEntity<?> getSingleBlog(@PathVariable("blogId") long blogId) {
		try {
			Blog blog = blogsRepository.getById(blogId);
			if (blog != null) {
				BlogForm blogForm = new BlogForm(blog.getBlogID(), blog.getContent(), blog.getDateBlogCreated(),
						blog.getHashtag(), new UserForm(blog.getUser().getUserId(), blog.getUser().getAvatar(),
								blog.getUser().getEmail(), blog.getUser().getUsername(), blog.getUser().getRole()));

				List<BlogCommentForm> listCmtForm = new ArrayList<>();
				List<BlogComment> listCmt = blogCommentRepo.findAllByBlog(blog);
				for (BlogComment i : listCmt) {
					BlogCommentForm form = new BlogCommentForm(i.getBlogCommentID(), i.getComment(), i.getCommentDate(),
							i.getNoOfVote(), i.getUser().getUserId(), i.getUser().getUsername());
					listCmtForm.add(form);
				}

				return ResponseEntity.ok(new SingleBlogPageResponse(blogForm, listCmtForm));

			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
			// TODO: handle exception
		}
	}
}
