package com.swp.vnhistory.dto.respone;

import java.util.List;

public class SingleEventPageResponse {

	private EventForm eventForm;
	private List<EventCommentForm> listEventComment;

	public EventForm getEventForm() {
		return eventForm;
	}

	public void setEventForm(EventForm eventForm) {
		this.eventForm = eventForm;
	}

	public List<EventCommentForm> getListEventComment() {
		return listEventComment;
	}

	public void setListEventComment(List<EventCommentForm> listEventComment) {
		this.listEventComment = listEventComment;
	}

	public SingleEventPageResponse(EventForm eventForm, List<EventCommentForm> listEventComment) {
		super();
		this.eventForm = eventForm;
		this.listEventComment = listEventComment;
	}

	public SingleEventPageResponse() {
		super();
	}

}
