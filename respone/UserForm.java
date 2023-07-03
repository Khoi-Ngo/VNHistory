package com.swp.vnhistory.dto.respone;

public class UserForm {

	private long userId;
	private String avatar;
	private String email;
	private String userName;
	private String Role;

	public UserForm(long userId, String avatar, String email, String userName) {
		super();
		this.userId = userId;
		this.avatar = avatar;
		this.email = email;
		this.userName = userName;
	}

	public UserForm(long userId, String avatar, String email, String userName, String role) {
		super();
		this.userId = userId;
		this.avatar = avatar;
		this.email = email;
		this.userName = userName;
		Role = role;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserForm() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
