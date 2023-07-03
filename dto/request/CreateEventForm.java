package com.swp.vnhistory.dto.request;

import java.time.LocalDate;

import javax.persistence.Lob;

public class CreateEventForm {

	private String content;
	private String eventName;
	private long dynastyId;
	private boolean outstanding;
	private String thumbnail;
	private String image;
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean isOutstanding() {
		return outstanding;
	}

	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}

	private long locationId;
	private long userId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public long getDynastyId() {
		return dynastyId;
	}

	public void setDynastyId(long dynastyId) {
		this.dynastyId = dynastyId;
	}

	public CreateEventForm() {
		super();
	}

	public CreateEventForm(String content, String eventName, long dynastyId, boolean outstanding, String thumbnail,
			String image, long locationId, long userId) {
		super();
		this.content = content;
		this.eventName = eventName;
		this.dynastyId = dynastyId;
		this.outstanding = outstanding;
		this.thumbnail = thumbnail;
		this.image = image;
		this.locationId = locationId;
		this.userId = userId;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
