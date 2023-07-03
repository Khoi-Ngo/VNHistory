package com.swp.vnhistory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sound.midi.Soundbank;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.swp.vnhistory.repository.ILocationRepo;

@Entity
@Table(name = "tbl_locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long locationID;

	@NotBlank
	@Size(max = 400)
	@Column(unique = true)
	private String locationName;

	@Size(max = 1000)
	private String locationDescription;

	public Location() {
		super();
	}

	public Location(long locationID, @NotBlank @Size(max = 400) String locationName,
			@Size(max = 1000) String locationDescription) {
		super();
		this.locationID = locationID;
		this.locationName = locationName;
		this.locationDescription = locationDescription;
	}

	public long getLocationID() {
		return locationID;
	}

	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public Location(@NotBlank @Size(max = 400) String locationName, @Size(max = 1000) String locationDescription) {
		super();
		this.locationName = locationName;
		this.locationDescription = locationDescription;
	}

	// Constructors, getters, and setters


}
