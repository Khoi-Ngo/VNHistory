package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

import javax.persistence.Lob;

public class EventCommentForm {

	private long eventCommentId;
	private LocalDate date;
	@Lob
	private String content;
	private long userId;
	private String userName;

	public long getEventCommentId() {
		return eventCommentId;
	}

	public void setEventCommentId(long eventCommentId) {
		this.eventCommentId = eventCommentId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public EventCommentForm(long eventCommentId, LocalDate date, String content, long userId, String userName) {
		super();
		this.eventCommentId = eventCommentId;
		this.date = date;
		this.content = content;
		this.userId = userId;
		this.userName = userName;
	}

	public EventCommentForm() {
		super();
	}

}
