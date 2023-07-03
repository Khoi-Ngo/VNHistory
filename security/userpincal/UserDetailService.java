package com.swp.vnhistory.security.userpincal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = (User) userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password: " + username));

		return UserPrinciple.build(user);

		
	}
}
