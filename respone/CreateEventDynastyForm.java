package com.swp.vnhistory.dto.respone;

public class CreateEventDynastyForm {

	private long dynastyId;
	private String dynastyName;

	public long getDynastyId() {
		return dynastyId;
	}

	public void setDynastyId(long dynastyId) {
		this.dynastyId = dynastyId;
	}

	public String getDynastyName() {
		return dynastyName;
	}

	public void setDynastyName(String dynastyName) {
		this.dynastyName = dynastyName;
	}

	public CreateEventDynastyForm(long dynastyId, String dynastyName) {
		super();
		this.dynastyId = dynastyId;
		this.dynastyName = dynastyName;
	}

	public CreateEventDynastyForm() {
		super();
	}

}
