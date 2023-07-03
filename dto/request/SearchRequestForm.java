package com.swp.vnhistory.dto.request;

public class SearchRequestForm {

	String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public SearchRequestForm(String keyword) {
		super();
		this.keyword = keyword;
	}
	public SearchRequestForm() {
		super();
		
	}
	
}
