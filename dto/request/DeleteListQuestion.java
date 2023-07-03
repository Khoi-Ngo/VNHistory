package com.swp.vnhistory.dto.request;

import java.util.List;

public class DeleteListQuestion {

	private List<Long> listQuestionId;

	public List<Long> getListQuestionId() {
		return listQuestionId;
	}

	public void setListQuestionId(List<Long> listQuestionId) {
		this.listQuestionId = listQuestionId;
	}
}
