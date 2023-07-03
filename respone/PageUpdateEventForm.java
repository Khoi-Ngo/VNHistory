package com.swp.vnhistory.dto.respone;

import javax.persistence.Lob;

public class PageUpdateEventForm {

	
	private PageEventCreate pageEventCreate;
	private long eventId;
	private String eventName;
	@Lob
	private String content;
	private String thumbnail;
	private String image;

	
	public PageUpdateEventForm(PageEventCreate pageEventCreate, long eventId, String eventName, String content,
			String thumbnail, String image, boolean outstanding) {
		super();
		this.pageEventCreate = pageEventCreate;
		this.eventId = eventId;
		this.eventName = eventName;
		this.content = content;
		this.thumbnail = thumbnail;
		this.image = image;
		this.outstanding = outstanding;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public PageEventCreate getPageEventCreate() {
		return pageEventCreate;
	}
	public void setPageEventCreate(PageEventCreate pageEventCreate) {
		this.pageEventCreate = pageEventCreate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isOutstanding() {
		return outstanding;
	}
	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}
	public PageUpdateEventForm() {
		super();
	}
	private boolean outstanding;
	
	
}
