package com.swp.vnhistory.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.PageEventCreate;
import com.swp.vnhistory.dto.respone.PageUpdateBlogForm;
import com.swp.vnhistory.dto.respone.PageUpdateEventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.service.impl.PageCreateUpdateService;

@RestController
public class PageCreateUpdateController {

	@Autowired
	PageCreateUpdateService pageCreateUpdateService;
	@Autowired
	IBlogsRepository blogsRepository;
	@Autowired
	IEventsRepository eventsRepository;

	// create event
	@GetMapping("/pageCreateEvent")
	@ResponseBody
	public ResponseEntity<?> getPageCreate() {
		// return ra cac location va dynasty
		return ResponseEntity.ok(pageCreateUpdateService.GetPageCreateEvent());
	}

	// update event
	@PostMapping("/pageUpdateEvent/{eventId}")
	@ResponseBody
	public ResponseEntity<?> getPageUpdateEvent(@PathVariable("eventId") long eventId) {
		try {
			Event event = eventsRepository.getById(eventId);
			if (event != null) {
				PageUpdateEventForm form = new PageUpdateEventForm(
						(PageEventCreate) pageCreateUpdateService.GetPageCreateEvent(), eventId, event.getEventName(),
						event.getContent(), event.getThumbnail(), event.getImage(), event.isOustanding());
				return ResponseEntity.ok(form);
			} else {
				return ResponseEntity.notFound().build(); // Return 404 if event is not found
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponeMessage("Something went wrong"));
		}
	}

	// update blog
	@GetMapping("/pageUpdateBlog/{blogId}")
	@ResponseBody
	public ResponseEntity<?> getPageUpdateBlog(@PathVariable("blogId") long blogId) {
		// return blog entity
		try {
			Blog blogEntity = blogsRepository.getById(blogId);
			PageUpdateBlogForm form = new PageUpdateBlogForm(blogEntity.getBlogID(), blogEntity.getContent(),
					blogEntity.getHashtag(), blogEntity.getUser().getUsername());
			return ResponseEntity.ok(form);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(new ResponeMessage("Something error"));
		}
	}

}
