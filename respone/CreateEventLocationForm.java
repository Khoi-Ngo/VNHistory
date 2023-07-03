package com.swp.vnhistory.dto.respone;

public class CreateEventLocationForm {

	private long locationId;
	private String locationName;
	public long getLocationId() {
		return locationId;
	}
	public CreateEventLocationForm() {
		super();
	}
	public CreateEventLocationForm(long locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
