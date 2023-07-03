package com.swp.vnhistory.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_events")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventID;

	@ManyToOne
	@JoinColumn(name = "dynastyID")
	private Dynasty dynasty;

	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "locationID")
	private Location location;

	@NotBlank
	@Lob
	@Column(unique = true)
	private String eventName;

	@NotBlank
	@Lob
	private String content;

	@Lob
	private String thumbnail;
	@Lob
	private String image;

	public Event(Dynasty dynasty, User user, Location location, @NotBlank String eventName, @NotBlank String content,
			String thumbnail, String image, boolean oustanding) {
		super();
		this.dynasty = dynasty;
		this.user = user;
		this.location = location;
		this.eventName = eventName;
		this.content = content;
		this.thumbnail = thumbnail;
		this.image = image;
		this.oustanding = oustanding;
		this.date_event_created = LocalDate.now();
	}

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

	private boolean oustanding;

	public boolean isOustanding() {
		return oustanding;
	}

	public void setOustanding(boolean oustanding) {
		oustanding = oustanding;
	}

	public LocalDate getDate_event_created() {
		return date_event_created;
	}

	public void setDate_event_created(LocalDate date_event_created) {
		this.date_event_created = date_event_created;
	}

	private LocalDate date_event_created;

	public long getEventID() {
		return eventID;
	}

	public void setEventID(long eventID) {
		this.eventID = eventID;
	}

	public Dynasty getDynasty() {
		return dynasty;
	}

	public void setDynasty(Dynasty dynasty) {
		this.dynasty = dynasty;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event() {
		super();
	}

	// Constructors, getters, and setters
}
