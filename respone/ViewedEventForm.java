package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

public class ViewedEventForm {

	private long viewId;
	private long eventId;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getViewId() {
		return viewId;
	}

	public ViewedEventForm(long viewId, long eventId, LocalDate date, String eventName, String shortContent,
			String thumbnail) {
		super();
		this.viewId = viewId;
		this.eventId = eventId;
		this.date = date;
		this.eventName = eventName;
		this.shortContent = shortContent;
		this.thumbnail = thumbnail;
	}

	public void setViewId(long viewId) {
		this.viewId = viewId;
	}

	public LocalDate getDate() {
		return date;
	}

	public ViewedEventForm() {
		super();
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	private LocalDate date;
	private String eventName;
	private String shortContent;
	private String thumbnail;

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}
