package com.swp.vnhistory.controller.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.BlogForm;
import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.dto.respone.HomepageResponse;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.repository.IBlogCommentRepo;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.service.impl.BlogServiceImpl;

@RestController
@RequestMapping("/homepage")
public class HomePageController {

	@Autowired
	IEventsRepository eventsRepository;
	@Autowired
	IBlogsRepository blogsRepository;
	@Autowired
	BlogServiceImpl blogServiceImpl;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getHomepage() {

		try {

			// list bai viet trong thang
			List<EventForm> listCurMonthEvent = eventsRepository.findEventFormsCreatedInCurrentMonth();
			// list bai viet noi bat
			List<EventForm> listOutstanding = eventsRepository.findOutstandingEvents();
			// blog danh gia cao nhat
			List<Blog> listHighBlog = blogsRepository.findBlogsOrderByVotes();
			List<BlogForm> listHighBlogForm = new ArrayList<>();

			for (Blog i : listHighBlog) {
				BlogForm blogForm = new BlogForm(i.getBlogID(), blogServiceImpl.getFirst15Words(i.getContent()),
						i.getDateBlogCreated(), i.getHashtag(),
						new UserForm(i.getUser().getUserId(), i.getUser().getAvatar(), i.getUser().getEmail(),
								i.getUser().getUsername(), i.getUser().getRole()));
				listHighBlogForm.add(blogForm);

			}

			// blog trong thang

			List<Blog> listCurMonthBlog = blogsRepository.findBlogsCreatedInCurrentMonth();
			List<BlogForm> listCurMonthBlogForm = new ArrayList<>();

			for (Blog i : listCurMonthBlog) {
				BlogForm blogForm = new BlogForm(i.getBlogID(), blogServiceImpl.getFirst15Words(i.getContent()),
						i.getDateBlogCreated(), i.getHashtag(),
						new UserForm(i.getUser().getUserId(), i.getUser().getAvatar(), i.getUser().getEmail(),
								i.getUser().getUsername(), i.getUser().getRole()));

				listCurMonthBlogForm.add(blogForm);

			}

			return ResponseEntity.ok(
					new HomepageResponse(listCurMonthEvent, listOutstanding, listHighBlogForm, listCurMonthBlogForm));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong"));
			// TODO: handle exception
		}
	}

}
