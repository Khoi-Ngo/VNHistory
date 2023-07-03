package com.swp.vnhistory.dto.request;

public class SavedChoice {

	private long questionId;
	private String optionChosen;
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getOptionChosen() {
		return optionChosen;
	}
	public void setOptionChosen(String optionChosen) {
		this.optionChosen = optionChosen;
	}
	
}
