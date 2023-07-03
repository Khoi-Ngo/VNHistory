package com.swp.vnhistory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_rankings")
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rankingId; 
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	public Ranking() {
		super();
	}

	public Ranking(User user, int total_score) {
		super();
		this.user = user;
		this.total_score = total_score;
	}

	public Ranking(long rankingId, User user, int total_score) {
		super();
		this.rankingId = rankingId;
		this.user = user;
		this.total_score = total_score;
	}

	public long getRankingId() {
		return rankingId;
	}

	public void setRankingId(long rankingId) {
		this.rankingId = rankingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	private int total_score;

	// Constructors, getters, and setters
	
}
