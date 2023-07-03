package com.swp.vnhistory.dto.respone;

public class EventForm {

	private long eventId;
	private String eventName;
	private String thumbnail;
	private String content;
	private String image;
	

	public long getEventId() {
		return eventId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public EventForm() {
		super();
	}

	public EventForm(long eventId, String eventName, String thumbnail, String content, String image) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.thumbnail = thumbnail;
		this.content = content;
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
