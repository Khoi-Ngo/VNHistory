package com.swp.vnhistory.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.CommentEvenFormRequest;
import com.swp.vnhistory.dto.request.DeleteEventCommentFormRequest;
import com.swp.vnhistory.dto.request.UpdateEventCommentFormRequest;
import com.swp.vnhistory.dto.respone.EventCommentForm;
import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.SingleEventPageResponse;
import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.EventComment;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IEventCommentRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.IUserRepository;

@RestController
@RequestMapping("/singleEvent")
public class SingleEventPage {

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IEventCommentRepo eventCommentRepo;

	@GetMapping("/{eventId}")
	@ResponseBody
	public ResponseEntity<?> getSingleEventPage(@PathVariable("eventId") long eventId) {
		// return bai viet va tat ca comment
		try {
			Event event = eventsRepository.getById(eventId);
			EventForm eventForm = new EventForm(event.getEventID(), event.getEventName(), event.getThumbnail(),
					event.getContent(), event.getImage());
			List<EventComment> listCmt = eventCommentRepo.findAllByEvent(event);
			List<EventCommentForm> listCmtForm = new ArrayList<>();
			for (EventComment i : listCmt) {
				EventCommentForm form = new EventCommentForm(i.getEventCommentID(), i.getCommentDate(), i.getContent(),
						i.getUser().getUserId(), i.getUser().getUsername());
				listCmtForm.add(form);
			}
			return ResponseEntity.ok(new SingleEventPageResponse(eventForm, listCmtForm));

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
			// TODO: handle exception
		}
	}

	@PostMapping("/comment")
	@ResponseBody
	public ResponseEntity<?> createComment(@Valid @RequestBody CommentEvenFormRequest form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Comment is empty"));
		} else {
			// call service
			try {
				User user = userRepository.getById(form.getUserId());
				Event event = eventsRepository.getById(form.getEventId());

				if (user == null || event == null) {
					return ResponseEntity.notFound().build();
//				return ResponseEntity.ok("1");
				}

				if (form.getContent() == null) {
					return ResponseEntity.ok(new ResponeMessage("empty content"));
				}

//				BlogComment blogComment = new BlogComment(user, blog, form.getContent(), LocalDate.now(), 0);
				EventComment eventComment = new EventComment(user, event, form.getContent(), LocalDate.now());
				eventCommentRepo.save(eventComment);
				return ResponseEntity.ok(new ResponeMessage("Done"));

			} catch (Exception e) {
//			return ResponseEntity.ok("2");
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}

		}

	}

	@DeleteMapping("/comment")
	@ResponseBody
	public ResponseEntity<?> deleteComment(@Valid @RequestBody DeleteEventCommentFormRequest form) {
		if (form == null) {
			// invalid
			return ResponseEntity.ok(new ResponeMessage("Cannot delete comment not found"));
		} else {
			try {
				EventComment eventComment = eventCommentRepo.getById(form.getEventCommentId());
				if (eventComment == null || eventComment.getUser().getUserId() != form.getUserId()) {
					return ResponseEntity.notFound().build();
				}
				eventCommentRepo.delete(eventComment);

				return ResponseEntity.ok(new ResponeMessage("DELETED"));

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong"));
				// TODO: handle exception
			}
		}

	}

	@PutMapping("/comment")
	@ResponseBody
	public ResponseEntity<?> updateComment(@Valid @RequestBody UpdateEventCommentFormRequest form) {
		if (form == null) {
			// invalid
			return ResponseEntity.ok(new ResponeMessage("Cannot update comment not found"));
		} else {
			try {
				EventComment eventComment = eventCommentRepo.getById(form.getEventCommentId());
				if (eventComment == null || eventComment.getUser().getUserId() != form.getUserId()) {
					return ResponseEntity.notFound().build();
				}
				// call repo
				eventCommentRepo.updateContentById(form.getEventCommentId(), form.getContent());

				return ResponseEntity.ok(new ResponeMessage("Updated"));

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong"));
				// TODO: handle exception
			}
		}

	}

}
