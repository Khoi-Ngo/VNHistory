package com.swp.vnhistory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_questionResults")
public class QuestionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionResultID;

	public long getQuestionResultID() {
		return questionResultID;
	}

	public void setQuestionResultID(long questionResultID) {
		this.questionResultID = questionResultID;
	}





	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	public QuestionResult() {
		super();
	}

	public QuestionResult(long questionResultID, User user, Quiz quiz, Question question, String optionChosen) {
		super();
		this.questionResultID = questionResultID;
		this.user = user;
		this.quiz = quiz;
		this.question = question;
		this.optionChosen = optionChosen;
	}

	public String getOptionChosen() {
		return optionChosen;
	}

	public void setOptionChosen(String optionChosen) {
		this.optionChosen = optionChosen;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	



	public QuestionResult(User user, Quiz quiz, Question question, String optionChosen) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.question = question;
		this.optionChosen = optionChosen;
	}





	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	@ManyToOne
	@JoinColumn(name = "quizID")
	private Quiz quiz;

	@ManyToOne
	@JoinColumn(name = "questionID")
	private Question question;

	private String optionChosen;

	// Constructors, getters, and setters
}
