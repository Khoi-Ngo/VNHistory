package com.swp.vnhistory.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.UpdatePasswordRequestForm;
import com.swp.vnhistory.dto.request.UpdateUserForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.UpdateUserAdminResponse;
//import com.swp.vnhistory.model.Role;
import com.swp.vnhistory.model.RoleName;
import com.swp.vnhistory.model.User;
//import com.swp.vnhistory.repository.AccountRepo;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class AccountServiceImpl {

	@Autowired
	IAccountRepository accountRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> SearchByUserName(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return accountRepository.findByUsernameContaining(keyword);
	}

	public Object DeleteAccount(Long userId) {

		User user = userRepository.getById(userId);
		if (user == null) {
			return new ResponeMessage("no account found");
		}

//		try {

		// DELETE HET TAT CA CAC TABLE LIEN QUAN => DELETE TABLE USER CUOI CUNG

		accountRepository.deleteById(userId);

		return new ResponeMessage("DELETED");

//		} catch (Exception e) {
//			e.getMessage();
//		}
//		return null;
	}

	public ResponeMessage updateUserPassword(UpdatePasswordRequestForm form) {

		User user = userRepository.getById(form.getUserId());
		// form gom userId + password
		if (form != null && form.getNewPass() != null && !form.getNewPass().equals("") && user != null) {
			// thay doi password + return response message
			accountRepository.updatePassword(form.getUserId(), passwordEncoder.encode(form.getNewPass()));
			return new ResponeMessage("UPDATE PASSWORD SUCCESSFULLY");
		} else {
			return new ResponeMessage("EMPTY PASSWORD OR USERID");
		}

	}

	public Object updateUser(UpdateUserForm form) {
		if (form != null) {
			if (form.getUserId() == null) {
				return new ResponeMessage("no user found");
			}
			if (form.getUserRole() == null && form.getUserName() == null && form.getEmail() == null) {
				return new ResponeMessage("no data to update");
			}

			User user = userRepository.getById(form.getUserId());
			if (form.getEmail() == null) {
				form.setEmail(user.getEmail());
			}
			if (form.getUserName() == null) {
				form.setUserName(user.getUsername());
			}
			if (form.getUserRole() == null) {
				form.setUserRole(user.getRole());
			}

			boolean checkRole = false;
			RoleName[] rolename = RoleName.values();
			for (RoleName i : rolename) {
				if (i.toString().equals(form.getUserRole())) {
					checkRole = true;
					break;
				}
			}
			if (!checkRole) {
				return ResponseEntity.ok(new ResponeMessage("INVALID ROLE OF USER"));
			}

//			return form.getUserRole();
			accountRepository.updateUser(form.getUserId(), form.getUserName(), form.getEmail(), form.getUserRole());

			return new UpdateUserAdminResponse(form.getUserId(), form.getUserName(), form.getEmail(),
					form.getUserRole(), new ResponeMessage("UPDATE USER OKE !"));
		} else {
			return new UpdateUserAdminResponse(null, null, null, null, new ResponeMessage("FORM UPATE IS INVALID"));
		}
	}

}
