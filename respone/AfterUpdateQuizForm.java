package com.swp.vnhistory.dto.respone;

public class AfterUpdateQuizForm {

//	private String userName;
	private String eventName;
	private Long quizId;
	private String quizName;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public AfterUpdateQuizForm(String eventName, Long quizId, String quizName) {
		super();

		this.eventName = eventName;
		this.quizId = quizId;
		this.quizName = quizName;
	}

	public AfterUpdateQuizForm() {
		super();
	}

}
