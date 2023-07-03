package com.swp.vnhistory.controller.page;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.SigninForm;
import com.swp.vnhistory.dto.request.SignupForm;
import com.swp.vnhistory.dto.respone.JwtRespone;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.model.Ranking;
//import com.swp.vnhistory.model.Role;
import com.swp.vnhistory.model.RoleName;
import com.swp.vnhistory.model.TypeUser;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IEnrollTimesRepo;
import com.swp.vnhistory.repository.IRankingRepository;
import com.swp.vnhistory.security.jwt.JwtTokenProvider;
import com.swp.vnhistory.security.userpincal.UserPrinciple;
import com.swp.vnhistory.service.impl.DashBoardAdminServiceImpl;
//import com.swp.vnhistory.service.impl.RoleServiceImpl;
import com.swp.vnhistory.service.impl.UserServiceImpl;

@RestController
public class AuthController {

	@Autowired
	UserServiceImpl userServiceImpl;
//	@Autowired
//	RoleServiceImpl roleServiceImpl;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	IRankingRepository rankingRepository;

	private static JwtRespone jwtResponse;

//	@PreAuthorize("hasRole('MEMBER')")
	@PostMapping("/signup")
	@ResponseBody // SIGNUP THÌ TRẢ VỀ MESSAGE

	public ResponseEntity<?> register(@Valid @RequestBody SignupForm signupForm) {
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
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.avatar = avatar;
//		this.role = role;
//		this.date = LocalDate.now();
		// check signupRole
		signupForm.setRole(RoleName.MEMBER.toString());

		User newUser = new User(signupForm.getUsername(), signupForm.getEmail(),
				passwordEncoder.encode(signupForm.getPassword()), signupForm.getAvatar(), signupForm.getRole());


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
		userServiceImpl.save(newUser);

		return new ResponseEntity<>(new ResponeMessage("Register successfully"), HttpStatus.OK);
	}

	@Autowired
	DashBoardAdminServiceImpl dashBoardAdminServiceImpl;

//	@PreAuthorize("hasRole ='ADMIN'")
	@PostMapping("/signin")
	@ResponseBody // Trả về nhiều thông tin quan trọng token


	public ResponseEntity<?> login(@Valid @RequestBody SigninForm signinForm) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinForm.getUsername(), signinForm.getPassword()));

		// SETTING AUTHEN
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.createToken(authentication);
		String refreshToken = jwtTokenProvider.createRefreshToken(authentication);

		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		// thuc hien +1 cho bang enroll times
		dashBoardAdminServiceImpl.addEnrollTimes(userPrinciple);

		this.jwtResponse = new JwtRespone(new UserForm(userPrinciple.getUserId(), userPrinciple.getAvatar(),
				userPrinciple.getEmail(), userPrinciple.getUsername(), userPrinciple.getRole()), token, refreshToken);

		return ResponseEntity.ok(jwtResponse);
	}

	@GetMapping("/reload")
	@ResponseBody
	public ResponseEntity<?> reloadPage() {
		return ResponseEntity.ok(this.jwtResponse);
	}

}
