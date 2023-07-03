package com.swp.vnhistory.dto.request;

import java.util.List;

public class AnswerSheet {

	private Long userId;
	private Long quizId;
	private List<SavedChoice> optionsChosen;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getQuizId() {
		return quizId;
	}
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}
	public List<SavedChoice> getOptionsChosen() {
		return optionsChosen;
	}
	public void setOptionsChosen(List<SavedChoice> optionsChosen) {
		this.optionsChosen = optionsChosen;
	}

	
	
}
