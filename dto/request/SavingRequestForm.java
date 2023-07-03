package com.swp.vnhistory.dto.request;

public class SavingRequestForm {

	private Long userId;
	private Long eventId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}
