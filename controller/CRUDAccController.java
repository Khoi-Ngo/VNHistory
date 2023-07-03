package com.swp.vnhistory.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.SearchRequestForm;
import com.swp.vnhistory.dto.request.SigninForm;
import com.swp.vnhistory.dto.request.SignupForm;
import com.swp.vnhistory.dto.request.UpdatePasswordRequestForm;
import com.swp.vnhistory.dto.request.UpdateUserForm;
import com.swp.vnhistory.dto.respone.JwtRespone;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Ranking;
import com.swp.vnhistory.model.RoleName;
import com.swp.vnhistory.model.TypeUser;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IRankingRepository;
import com.swp.vnhistory.security.jwt.JwtEntryPoint;
import com.swp.vnhistory.security.userpincal.UserPrinciple;
import com.swp.vnhistory.service.impl.AccountServiceImpl;

import com.swp.vnhistory.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/admin/accountList")
public class CRUDAccController {

	@Autowired
	AccountServiceImpl accountServiceImpl;

	@Autowired
	IRankingRepository rankingRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;

	// DELETE ACCOUNT
	@DeleteMapping("/account/{userId}")
	@ResponseBody
	public ResponseEntity<?> deleteAccount(@PathVariable("userId") int userId) {
		try {
			return ResponseEntity.ok(accountServiceImpl.DeleteAccount(new Long(userId)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponeMessage("Failed to delete account"));
		}
	}

	// CREATE ACCOUNT

	@PostMapping("/account")
	@ResponseBody // create THÌ TRẢ VỀ MESSAGE
	public ResponseEntity<?> createAccout(@Valid @RequestBody SignupForm signupForm) {
		if (userServiceImpl.existByUsername(signupForm.getUsername())) {
			return new ResponseEntity<>(new ResponeMessage("The username is already in use"), HttpStatus.OK);
		}
		if (userServiceImpl.existByEmail(signupForm.getEmail())) {
			return new ResponseEntity<>(new ResponeMessage("The email is already in use"), HttpStatus.OK);
		}

		// SETTING DEFAULT AVATAR
		if (signupForm.getAvatar() == null || signupForm.getAvatar().trim().isEmpty()) {
			signupForm.setAvatar("https://www.google.com/url?sa=i&url=https%3"
					+ "A%2F%2Fwww.pinterest.com%2Fpin%2F946107834193014928%2F&psig=AOvVaw0QIyVe-z_"
					+ "YaqTwYHKfLZ9Q&ust=168581910324800"
					+ "0&source=images&cd=vfe&ved=0CBEQjRxqFwoTCMC-mNijpf8CFQAAAAAdAAAAABAE");
		}
		// CREATING NEW USER
		// SET ROLE
		boolean checkRole = false;
		RoleName[] rolename = RoleName.values();
		for (RoleName i : rolename) {
			if (i.toString().equals(signupForm.getRole())) {
				checkRole = true;
				break;
			}
		}
		if (!checkRole) {
			return ResponseEntity.ok(new ResponeMessage("INVALID ROLE OF USER"));
		}

		User newUser = new User(signupForm.getUsername(), signupForm.getEmail(),
				passwordEncoder.encode(signupForm.getPassword()), signupForm.getAvatar(), signupForm.getRole());

		// ======

		if (signupForm.getRole().equals(RoleName.MEMBER.toString())) {
			newUser.setTypeUser(TypeUser.BRONZE.toString());

			try {
				Ranking ranking = new Ranking(newUser, 0);
				rankingRepository.save(ranking);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		} else {
			newUser.setTypeUser(TypeUser.NA.toString());
		}

		// ======
		userServiceImpl.save(newUser);

		return new ResponseEntity<>(new ResponeMessage("Register successfully"), HttpStatus.OK);
	}

	// UPDATE ACCOUNT

	// UPDATE ACCOUNT FORM (USER NAME AND ROLE)
	@PutMapping("/account")
	@ResponseBody
	public ResponseEntity<?> updateAccount(@Valid @RequestBody UpdateUserForm updateUserForm) {
		if (updateUserForm == null) {
			return ResponseEntity.ok(new ResponeMessage("form invalid - controller alert"));
		}

		return ResponseEntity.ok(accountServiceImpl.updateUser(updateUserForm));

	}

	// UPDATE PASSWORD INCASE FORGET
	@PutMapping("/account/password")
	@ResponseBody
	public ResponseEntity<?> updatePasswordAccount(@Valid @RequestBody UpdatePasswordRequestForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("form invalid - controller alert"));
		}
		return ResponseEntity.ok(accountServiceImpl.updateUserPassword(form));
	}

//	 SEARCH ACCOUNT
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> searchAccount(@Valid @RequestBody SearchRequestForm searchRequestForm) {
		// nhận list
		if (searchRequestForm == null) {
			searchRequestForm = new SearchRequestForm("");
		}

		return ResponseEntity.ok(accountServiceImpl.SearchByUserName(searchRequestForm.getKeyword()));

	}

}
