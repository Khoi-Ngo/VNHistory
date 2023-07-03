package com.swp.vnhistory.dto.respone;

import java.util.List;

public class MemberProfileForm {

	private UserForm userForm;
	private List<EventForm> listReadEventsForm;

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public List<EventForm> getListReadEventsForm() {
		return listReadEventsForm;
	}

	public MemberProfileForm() {
		super();
	}

	public MemberProfileForm(UserForm userForm, List<EventForm> listReadEventsForm) {
		super();
		this.userForm = userForm;
		this.listReadEventsForm = listReadEventsForm;
	}

	public void setListReadEventsForm(List<EventForm> listReadEventsForm) {
		this.listReadEventsForm = listReadEventsForm;
	}

}
