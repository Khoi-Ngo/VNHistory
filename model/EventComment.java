package com.swp.vnhistory.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_event_comments")
public class EventComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventCommentID;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "eventID")
	private Event event;

	public EventComment(User user, Event event, @NotBlank String content, LocalDate commentDate) {
		super();
		this.user = user;
		this.event = event;
		this.content = content;
		this.commentDate = commentDate;
	}

	@NotBlank
	@Lob
	private String content;

	private LocalDate commentDate;

//    private int noOfVote;

	public long getEventCommentID() {
		return eventCommentID;
	}

	public void setEventCommentID(long eventCommentID) {
		this.eventCommentID = eventCommentID;
	}

	public EventComment() {
		super();
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EventComment(long eventCommentID, User user, Event event, @NotBlank String content, LocalDate commentDate) {
		super();
		this.eventCommentID = eventCommentID;
		this.user = user;
		this.event = event;
		this.content = content;
		this.commentDate = commentDate;

	}

	// Constructors, getters, and setters
}
