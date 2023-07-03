package com.swp.vnhistory.dto.respone;

import java.time.LocalDate;

public class QuizResultForm {

	private long quizId;
	private long resultId;

	private LocalDate date;
	private String quizName;

	private String eventThumbnail;

	private int score;

	public String getEventThumbnail() {
		return eventThumbnail;
	}

	public void setEventThumbnail(String eventThumbnail) {
		this.eventThumbnail = eventThumbnail;
	}

	public QuizResultForm(long quizId, long resultId, LocalDate date, String quizName, String eventThumbnail,
			int score) {
		super();
		this.quizId = quizId;
		this.resultId = resultId;
		this.date = date;
		this.quizName = quizName;
		this.eventThumbnail = eventThumbnail;
		this.score = score;
	}

	public QuizResultForm() {
		super();
	}

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public long getResultId() {
		return resultId;
	}

	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
