package com.swp.vnhistory.dto.respone;

public class NoOfEvent_Location {

	private int count;
	private int months;
	private int year;
	private int locationId;
	
	private String locatioName;

	public int getCount() {
		return count;
	}

	public NoOfEvent_Location() {
		super();
	}


	public NoOfEvent_Location(int count, int months, int year, int locationId, String locatioName) {
		super();
		this.count = count;
		this.months = months;
		this.year = year;
		this.locationId = locationId;
		this.locatioName = locatioName;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLocatioName() {
		return locatioName;
	}

	public void setLocatioName(String locatioName) {
		this.locatioName = locatioName;
	}
}
