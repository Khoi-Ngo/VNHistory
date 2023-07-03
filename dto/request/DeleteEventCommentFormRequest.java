package com.swp.vnhistory.dto.request;

public class DeleteEventCommentFormRequest {

	private long eventCommentId;
	private long userId;

	public long getEventCommentId() {
		return eventCommentId;
	}

	public void setEventCommentId(long eventCommentId) {
		this.eventCommentId = eventCommentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
