//package com.swp.vnhistory.security.userpincal;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.swp.vnhistory.model.User;
//
//public class UserPrinciple implements UserDetails {
//
//	private long userId;
//	private String username;
//	private String email;
//	@JsonIgnore
//	private String password;
//	private String avatar;
//	private String role;;
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return roles;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return password;
//	}
//
//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getAvatar() {
//		return avatar;
//	}
//
//	public void setAvatar(String avatar) {
//		this.avatar = avatar;
//	}
//
//	public Collection<? extends GrantedAuthority> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Collection<? extends GrantedAuthority> roles) {
//		this.roles = roles;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	public UserPrinciple() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public UserPrinciple(long userId, String username, String email, String password, String avatar,
//			Collection<? extends GrantedAuthority> roles) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.avatar = avatar;
//		this.roles = roles;
//	}
//
//	public static UserPrinciple build(User user) {
////		List<GrantedAuthority> authorities = 
////				user.getRoles().stream().
////				map(role -> new SimpleGrantedAuthority(role.getName().name())).
////				collect(Collectors.toList()); 
//		GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().toString());
//
//		return new UserPrinciple(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(),
//				user.getAvatar(), (Collection<? extends GrantedAuthority>) authorities);
//	}
//}

package com.swp.vnhistory.security.userpincal;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swp.vnhistory.model.User;

//import io.jsonwebtoken.lang.Collections;

public class UserPrinciple implements UserDetails {

	private long userId;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private String avatar;
	private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}

//	public Collection<? extends GrantedAuthority> getAuthorities() {
//        // TODO Auto-generated method stub
//        return new SimpleGrantedAuthority(role);
//    }

//   
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UserPrinciple() {
		// TODO Auto-generated constructor stub
	}

	public UserPrinciple(long userId, String username, String email, String password, String avatar, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
	}

	public static UserPrinciple build(User user) {
		return new UserPrinciple(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getAvatar(), user.getRole().toString());
	}
}
