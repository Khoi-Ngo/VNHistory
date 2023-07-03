package com.swp.vnhistory.dto.respone;

import java.util.List;

import com.swp.vnhistory.model.Question;
import com.swp.vnhistory.model.QuestionResult;

public class ReviewQuizForm {

	//quiz id  + userid  + question id -> optionchosen
	
	// question id - question content - dap an da chon -> question + questionresult
	private Question question;
	private QuestionResult questionResult;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionResult getQuestionResult() {
		return questionResult;
	}
	public void setQuestionResult(QuestionResult questionResult) {
		this.questionResult = questionResult;
	}
	public ReviewQuizForm(Question question, QuestionResult questionResult) {
		super();
		this.question = question;
		this.questionResult = questionResult;
	}
	
	
}
