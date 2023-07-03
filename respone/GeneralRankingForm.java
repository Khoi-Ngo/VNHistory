package com.swp.vnhistory.dto.respone;

public class GeneralRankingForm {
	//bxh
	//1 obj tuong uong voi 1 row
	private String userName;
	private int scores;
	private int numbOfQuizzes;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getScores() {
		return scores;
	}
	public GeneralRankingForm(String userName, int scores, int numbOfQuizzes) {
		super();
		this.userName = userName;
		this.scores = scores;
		this.numbOfQuizzes = numbOfQuizzes;
	}
	public GeneralRankingForm() {
		// TODO Auto-generated constructor stub
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public int getNumbOfQuizzes() {
		return numbOfQuizzes;
	}
	public void setNumbOfQuizzes(int numbOfQuizzes) {
		this.numbOfQuizzes = numbOfQuizzes;
	}
	
	
	
}
