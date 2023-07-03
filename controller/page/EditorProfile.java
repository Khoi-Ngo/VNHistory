package com.swp.vnhistory.controller.page;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.ChangeProfileReq;
import com.swp.vnhistory.dto.request.EditorDashboardRequest;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IUserRepository;
import com.swp.vnhistory.service.impl.EditorDashboardService;

@RestController
@RequestMapping("/editorprofile")
public class EditorProfile {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	EditorDashboardService editorDashboardService;

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> getEditorProfile(@Valid @RequestBody ChangeProfileReq userIdForm) {
		try {
			Optional<UserForm> optionalUserForm = userRepository.findUserFormById(userIdForm.getUserId());

			if (optionalUserForm.isPresent()) {
				UserForm userForm = optionalUserForm.get();
				// query to return list eventform
				return ResponseEntity.ok(userForm);

			} else {
				return ResponseEntity.notFound().build(); // 404 if user not found

			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(new ResponeMessage("Something wrong"));
		}
	}

	// dashboard
	// luot xem trong cac thang cua bai viet cua minh + so quiz da tao + so bai viet
	// da tao

	@GetMapping("/dashboard/eventsLocation/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> eventsLocation(@PathVariable("noOfMonths") long noOfMonths) {

		try {

			return ResponseEntity.ok(editorDashboardService.getEventsLocation(noOfMonths));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong here"));
			// TODO: handle exception
		}

	}

	@GetMapping("/dashboard/eventsDynasty/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> eventsDynasty(@PathVariable("noOfMonths") long noOfMonths) {

		try {

			return ResponseEntity.ok(editorDashboardService.getEventsDynasty(noOfMonths));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong here"));
			// TODO: handle exception
		}

	}

	@GetMapping("/dashboard/quizzes/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getQuizzes(@PathVariable("noOfMonths") long noOfMonths) {

		try {

			return ResponseEntity.ok(editorDashboardService.getQuizzesCreated(noOfMonths));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong here"));
			// TODO: handle exception
		}

	}

	@GetMapping("/dashboard/events/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getEvents(@PathVariable("noOfMonths") long noOfMonths) {

		try {

			return ResponseEntity.ok(editorDashboardService.getNewEvents(noOfMonths));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something wrong here"));
			// TODO: handle exception
		}
	}

}
