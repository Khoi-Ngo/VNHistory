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
@Table(name = "tbl_quizResults")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quizID")
    private Quiz quiz;

    private LocalDate date;
    //when done the quiz 
    

	public long getResultID() {
		return resultID;
	}

	public QuizResult(long resultID, User user, Quiz quiz, LocalDate date, int result) {
		super();
		this.resultID = resultID;
		this.user = user;
		this.quiz = quiz;
		this.date = date;
		this.result = result;
	}

	public QuizResult(User user, Quiz quiz, LocalDate date, int result) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.date = date;
		this.result = result;
	}

	public QuizResult() {
		super();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setResultID(long resultID) {
		this.resultID = resultID;
	}

	



	public QuizResult(long resultID, User user, Quiz quiz, int result) {
		super();
		this.resultID = resultID;
		this.user = user;
		this.quiz = quiz;
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	private int result;

    // Constructors, getters, and setters
}
