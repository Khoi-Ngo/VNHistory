package com.swp.vnhistory.model;

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
@Table(name = "tbl_questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionID;

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public Question() {
		super();
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public Question(Quiz quiz, @NotBlank String question, @NotBlank String option1, @NotBlank String option2,
			String option3, String option4, @NotBlank String correctAnswer) {
		super();
		this.quiz = quiz;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@ManyToOne
	@JoinColumn(name = "quizID")
	private Quiz quiz;

	@NotBlank
	@Lob
	private String question;

	@NotBlank
	@Lob
	private String option1;

	@NotBlank
	@Lob
	private String option2;

	@Lob
	private String option3;

	@Lob
	private String option4;

	@NotBlank
	@Lob
	private String correctAnswer;

	public Question(long questionID, Quiz quiz, @NotBlank String question, @NotBlank String option1,
			@NotBlank String option2, String option3, String option4, @NotBlank String correctAnswer) {
		super();
		this.questionID = questionID;
		this.quiz = quiz;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
	}

	// Constructors, getters, and setters
}
