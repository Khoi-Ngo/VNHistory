package com.swp.vnhistory.dto.respone;

import com.swp.vnhistory.model.QuizResult;

public class AfterDoQuizForm {

	private QuizResultForm quizResultForm;
	private int maxResult;

	public QuizResultForm getQuizResultForm() {
		return quizResultForm;
	}

	public void setQuizResultForm(QuizResultForm quizResultForm) {
		this.quizResultForm = quizResultForm;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public AfterDoQuizForm(QuizResultForm quizResultForm, int maxResult) {
		super();
		this.quizResultForm = quizResultForm;
		this.maxResult = maxResult;
	}

	public AfterDoQuizForm() {
		super();
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
