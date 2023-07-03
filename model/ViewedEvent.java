package com.swp.vnhistory.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_viewedEvents")
public class ViewedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long viewID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "eventsRead")
    private Event event;

    private LocalDate dateRead;

	
	public ViewedEvent() {
		super();
	}

	public long getViewID() {
		return viewID;
	}

	public void setViewID(long viewID) {
		this.viewID = viewID;
	}

	
	

	public ViewedEvent(long viewID, User user, Event event, LocalDate dateRead) {
		super();
		this.viewID = viewID;
		this.user = user;
		this.event = event;
		this.dateRead = dateRead;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public LocalDate getDateRead() {
		return dateRead;
	}

	public void setDateRead(LocalDate dateRead) {
		this.dateRead = dateRead;
	}

    // Constructors, getters, and setters
}

