package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

public class SavedEventForm {

	private long saveId;
	private long eventId;
	private String eventName;
	private LocalDate date;
	
	private String thumbnail;
	private String shortContent;
	
	public long getSaveId() {
		return saveId;
	}
	public void setSaveId(long saveId) {
		this.saveId = saveId;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	public SavedEventForm() {
		super();
	}

	public SavedEventForm(long saveId, long eventId, String eventName, LocalDate date, String thumbnail,
			String shortContent) {
		super();
		this.saveId = saveId;
		this.eventId = eventId;
		this.eventName = eventName;
		this.date = date;
		this.thumbnail = thumbnail;
		this.shortContent = shortContent;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
