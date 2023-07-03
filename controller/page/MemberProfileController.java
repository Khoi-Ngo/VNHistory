package com.swp.vnhistory.controller.page;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.ChangeProfileReq;
//import com.swp.vnhistory.dto.request.UpdateProfileForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.IUserRepository;
import com.swp.vnhistory.service.impl.MemberProfileService;
//import com.swp.vnhistory.service.impl.ProfileService;

@RestController
@RequestMapping("/memberprofile")
public class MemberProfileController {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	MemberProfileService profileService;

	@PostMapping
	@ResponseBody // list bai viet da xem + userName + them type user va rank -> them vao JWT
					// respone luon
	public ResponseEntity<?> getMemberProfile(@Valid @RequestBody ChangeProfileReq userIdForm) {
//return user form va event viewed
		if (userIdForm == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(profileService.getMemberProfile(userIdForm));
		}
	}

	@PostMapping("/viewedEvent")
	@ResponseBody
	public ResponseEntity<?> getViewedEvent(@Valid @RequestBody ChangeProfileReq userIdForm) {
		if (userIdForm == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(profileService.getReadEvent(userIdForm));
		}
	}

	// delete read event
	@DeleteMapping("/readEvent/{eventId}")
	@ResponseBody
	public ResponseEntity<?> deleteReadEvent(@PathVariable("eventId") long eventId) {
		try {
			Event event = eventsRepository.getById(eventId);
			if (event != null) {
				eventsRepository.delete(event);
				return ResponseEntity.ok(new ResponeMessage("DELETED"));
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			// TODO: handle exception

			return ResponseEntity.ok(new ResponeMessage("Something wrong"));
		}
	}

	@PostMapping("/savedEvent")
	@ResponseBody // list saved event + userName
	public ResponseEntity<?> getSavedEvent(@Valid @RequestBody ChangeProfileReq userIdForm) {
		if (userIdForm == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(profileService.getSavedEvent(userIdForm));
		}
	}

	@PostMapping("/doneQuizzes")
	@ResponseBody // list quiz da lam + userName + ranking
	public ResponseEntity<?> getDoneQuiz(@Valid @RequestBody ChangeProfileReq userIdForm) {
		try {
			if (userIdForm != null) {
				return ResponseEntity.ok(profileService.getQuizDone(userIdForm));

			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong here"));
			// TODO: handle exception
		}
	}

}
