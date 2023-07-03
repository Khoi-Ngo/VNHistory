package com.swp.vnhistory.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_enrollTimes")
public class EnrollTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enrollTimesId;
	
	
	public EnrollTime() {
		super();
	}
	private long times;
	@Column(unique = true)
	private LocalDate date;
	public long getEnrollTimesId() {
		return enrollTimesId;
	}
	public void setEnrollTimesId(long enrollTimesId) {
		this.enrollTimesId = enrollTimesId;
	}
	public EnrollTime(long enrollTimesId, long times, LocalDate date) {
		super();
		this.enrollTimesId = enrollTimesId;
		this.times = times;
		this.date = date;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
