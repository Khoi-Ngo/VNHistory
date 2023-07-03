package com.swp.vnhistory.dto.respone;

public class UpdateUserAdminResponse {

	Long userId;
	String userName;
	String email;
	ResponeMessage responeMessage;
	String role;
	public ResponeMessage getResponeMessage() {
		return responeMessage;
	}
	public void setResponeMessage(ResponeMessage responeMessage) {
		this.responeMessage = responeMessage;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UpdateUserAdminResponse(Long userId, String userName, String email, String role,ResponeMessage responeMessage) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.role = role;
		this.responeMessage = responeMessage;
	}
	
}
