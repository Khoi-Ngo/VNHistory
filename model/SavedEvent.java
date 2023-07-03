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
@Table(name = "tbl_savedEvents")
public class SavedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saveID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SavedEvent(User user, Event event) {
		super();
		this.user = user;
		this.event = event;
		this.dateSave = LocalDate.now();
	}

	public SavedEvent() {
		super();
	}

	@ManyToOne
    @JoinColumn(name = "eventsSaved")
    private Event event;

    
    private LocalDate dateSave;

	public long getSaveID() {
		return saveID;
	}

	public void setSaveID(long saveID) {
		this.saveID = saveID;
	}

	


	public SavedEvent(long saveID, User user, Event event, LocalDate dateSave) {
		super();
		this.saveID = saveID;
		this.user = user;
		this.event = event;
		this.dateSave = dateSave;
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

	public LocalDate getDateSave() {
		return dateSave;
	}

	public void setDateSave(LocalDate dateSave) {
		this.dateSave = dateSave;
	}


    // Constructors, getters, and setters
	
}
