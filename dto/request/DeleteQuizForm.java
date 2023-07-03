package com.swp.vnhistory.dto.request;

public class DeleteQuizForm {

	 private Long quizId;

	    public DeleteQuizForm() {
	        // Default constructor
	    }

	    public DeleteQuizForm(Long quizId) {
	        this.quizId = quizId;
	    }

	    public Long getQuizId() {
	        return quizId;
	    }

	    public void setQuizId(Long quizId) {
	        this.quizId = quizId;
	    }
	
}
