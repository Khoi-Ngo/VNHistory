package com.swp.vnhistory.dto.respone;

import java.util.List;

public class PageEventCreate {

	// location id - location name
	List<CreateEventLocationForm> listLocationForms;
	// dynasty id - dynasty name
	List<CreateEventDynastyForm> listDynastyForms;

	public List<CreateEventLocationForm> getListLocationForms() {
		return listLocationForms;
	}

	public void setListLocationForms(List<CreateEventLocationForm> listLocationForms) {
		this.listLocationForms = listLocationForms;
	}

	public List<CreateEventDynastyForm> getListDynastyForms() {
		return listDynastyForms;
	}

	public void setListDynastyForms(List<CreateEventDynastyForm> listDynastyForms) {
		this.listDynastyForms = listDynastyForms;
	}

	public PageEventCreate(List<CreateEventLocationForm> listLocationForms,
			List<CreateEventDynastyForm> listDynastyForms) {
		super();
		this.listLocationForms = listLocationForms;
		this.listDynastyForms = listDynastyForms;
	}

	public PageEventCreate() {
		super();
	}

}
