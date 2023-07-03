package com.swp.vnhistory.dto.request;

public class DeleteEventForm {

	private long eventId;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public DeleteEventForm(long eventId) {
		super();
		this.eventId = eventId;
	}

	public DeleteEventForm() {
		super();

	}

}
