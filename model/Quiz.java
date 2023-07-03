package com.swp.vnhistory.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_quizzes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @NotBlank
    @Lob
    @Column(unique = true)
    private String quizName;

    

	public Quiz(User user, @NotBlank String quizName, LocalDate dateQuizCreated, Event event) {
		super();
		this.user = user;
		this.quizName = quizName;
		this.dateQuizCreated = dateQuizCreated;
		this.event = event;
	}

	public Quiz() {
		super();
	}

	private LocalDate dateQuizCreated;

    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;

	public Long getQuizID() {
		return quizID;
	}

	public void setQuizID(Long quizID) {
		this.quizID = quizID;
	}

	

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public LocalDate getDateQuizCreated() {
		return dateQuizCreated;
	}

	public void setDateQuizCreated(LocalDate dateQuizCreated) {
		this.dateQuizCreated = dateQuizCreated;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz(Long quizID, User user, @NotBlank String quizName, LocalDate dateQuizCreated, Event event) {
		super();
		this.quizID = quizID;
		this.user = user;
		this.quizName = quizName;
		this.dateQuizCreated = dateQuizCreated;
		this.event = event;
	}

	public void setQuizID(long quizID) {
		this.quizID = quizID;
	}

	public Quiz(User user, @NotBlank String quizName, Event event) {
		super();
		this.user = user;
		this.quizName = quizName;
		this.event = event;
	}

	

	

    // Constructors, getters, and setters
    
}
