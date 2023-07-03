package com.swp.vnhistory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_dynasties")
public class Dynasty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dynastyID;

	@NotBlank
	@Size(max = 400)
	@Column(unique = true)
	private String dynastyName;

	public Dynasty(@NotBlank @Size(max = 400) String dynastyName) {
		super();
		this.dynastyName = dynastyName;
	}

	public Dynasty(@NotBlank @Size(max = 400) String dynastyName,
			@NotBlank @Size(max = 10000) String dynastyDescription) {
		super();
		this.dynastyName = dynastyName;
		this.dynastyDescription = dynastyDescription;
	}

	public Dynasty() {
		super();
	}

	public Dynasty(long dynastyID, @NotBlank @Size(max = 400) String dynastyName,
			@NotBlank @Size(max = 10000) String dynastyDescription) {
		super();
		this.dynastyID = dynastyID;
		this.dynastyName = dynastyName;
		this.dynastyDescription = dynastyDescription;
	}

	public long getDynastyID() {
		return dynastyID;
	}

	public void setDynastyID(long dynastyID) {
		this.dynastyID = dynastyID;
	}

	public String getDynastyName() {
		return dynastyName;
	}

	public void setDynastyName(String dynastyName) {
		this.dynastyName = dynastyName;
	}

	public String getDynastyDescription() {
		return dynastyDescription;
	}

	public void setDynastyDescription(String dynastyDescription) {
		this.dynastyDescription = dynastyDescription;
	}

	@NotBlank
	@Size(max = 10000)
	private String dynastyDescription;

	@Column(unique = true)
	private int startYearPhase;
	@Column(unique = true)
	private int endYearPhase;

	public Dynasty(long dynastyID, @NotBlank @Size(max = 400) String dynastyName,
			@NotBlank @Size(max = 10000) String dynastyDescription, int startYearPhase, int endYearPhase) {
		super();
		this.dynastyID = dynastyID;
		this.dynastyName = dynastyName;
		this.dynastyDescription = dynastyDescription;
		this.startYearPhase = startYearPhase;
		this.endYearPhase = endYearPhase;
	}

	public Dynasty(@NotBlank @Size(max = 400) String dynastyName, @NotBlank @Size(max = 400) String dynastyDescription,
			int startYearPhase, int endYearPhase) {
		super();
		this.dynastyName = dynastyName;
		this.dynastyDescription = dynastyDescription;
		this.startYearPhase = startYearPhase;
		this.endYearPhase = endYearPhase;
	}

	public int getStartYearPhase() {
		return startYearPhase;
	}

	public void setStartYearPhase(int startYearPhase) {
		this.startYearPhase = startYearPhase;
	}

	public int getEndYearPhase() {
		return endYearPhase;
	}

	public void setEndYearPhase(int endYearPhase) {
		this.endYearPhase = endYearPhase;
	}

	// Constructors, getters, and setters
}

