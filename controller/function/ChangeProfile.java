package com.swp.vnhistory.controller.function;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.ChangeProfileReq;
import com.swp.vnhistory.dto.request.UpdateUserForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IUserRepository;

@RestController
@RequestMapping("/changeprofile")
public class ChangeProfile {

	@Autowired
	IUserRepository userRepository;

	// preview
	@PostMapping("/preview")
	@ResponseBody
	public ResponseEntity<?> preview(@Valid @RequestBody ChangeProfileReq form) {
		try {
			Optional<UserForm> optionalUserForm = userRepository.findUserFormById(form.getUserId());

			if (optionalUserForm.isPresent()) {
				UserForm userForm = optionalUserForm.get();
				return ResponseEntity.ok(userForm);
			} else {
				return ResponseEntity.notFound().build(); // 404 if user not found
//				return ResponseEntity.ok(new ResponeMessage("User null"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(new ResponeMessage("Something wrong"));
		}
	}

	// submit
	@PostMapping("/submit")
	@ResponseBody
	public ResponseEntity<?> submit(@Valid @RequestBody UserForm form) {
		try {
			if (form == null) {
				return ResponseEntity.notFound().build();
			}
			Optional<UserForm> optionalUserForm = userRepository.findUserFormById(form.getUserId());

			UserForm oldForm;
			if (optionalUserForm.isPresent()) {
				oldForm = optionalUserForm.get();
//				return ResponseEntity.ok(userForm);
			} else {
				return ResponseEntity.notFound().build(); // 404 if user not found

			}

			if (form.getAvatar() == null || form.getAvatar().isEmpty()) {
				form.setAvatar(oldForm.getAvatar());
			}
			if (form.getEmail() == null || form.getEmail().isEmpty()) {
				form.setEmail(oldForm.getEmail());
			}
			if (form.getUserName() == null || form.getUserName().isEmpty()) {
				form.setUserName(oldForm.getUserName());
			}

			// query update
			userRepository.updateUserDetails(form.getUserId(), form.getAvatar(), form.getEmail(), form.getUserName());

			return ResponseEntity.ok(new ResponeMessage("UPDATED USER"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.ok(new ResponeMessage("Something wrong"));
		}
	}

}
