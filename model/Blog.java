package com.swp.vnhistory.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_blogs")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blogID;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotBlank
	@Lob
	private String content;
//
//	@NotBlank
//	private boolean highlight;

	@NotBlank
	private String hashtag;

	public Blog(User user, @NotBlank String content, @NotBlank String hashtag, LocalDate dateBlogCreated) {
		super();
		this.user = user;
		this.content = content;
		this.hashtag = hashtag;
		this.dateBlogCreated = dateBlogCreated;
	}

	public Blog(User user, @NotBlank String content, @NotBlank String hashtag, LocalDate dateBlogCreated, int noOfView,
			int noOfVote) {
		super();
		this.user = user;
		this.content = content;

		this.hashtag = hashtag;
		this.dateBlogCreated = dateBlogCreated;
		this.noOfView = noOfView;
		this.noOfVote = noOfVote;
	}

	public Blog(long blogID, User user, @NotBlank String content, @NotBlank String hashtag, LocalDate dateBlogCreated,
			int noOfView, int noOfVote) {
		super();
		this.blogID = blogID;
		this.user = user;
		this.content = content;

		this.hashtag = hashtag;
		this.dateBlogCreated = dateBlogCreated;
		this.noOfView = noOfView;
		this.noOfVote = noOfVote;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public Blog(User user, @NotBlank String content, LocalDate dateBlogCreated) {
		super();
		this.user = user;
		this.content = content;
		this.dateBlogCreated = dateBlogCreated;
	}

	private LocalDate dateBlogCreated;

	private int noOfView;

	private int noOfVote;

	public Blog() {
		super();
	}

	public long getBlogID() {
		return blogID;
	}

	public void setBlogID(long blogID) {
		this.blogID = blogID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blog(User user, @NotBlank String content, LocalDate dateBlogCreated, int noOfView, int noOfVote) {
		super();
		this.user = user;
		this.content = content;
		this.dateBlogCreated = dateBlogCreated;
		this.noOfView = noOfView;
		this.noOfVote = noOfVote;
	}

	public LocalDate getDateBlogCreated() {
		return dateBlogCreated;
	}

	public void setDateBlogCreated(LocalDate dateBlogCreated) {
		this.dateBlogCreated = dateBlogCreated;
	}

	public int getNoOfView() {
		return noOfView;
	}

	public void setNoOfView(int noOfView) {
		this.noOfView = noOfView;
	}

	public int getNoOfVote() {
		return noOfVote;
	}

	public void setNoOfVote(int noOfVote) {
		this.noOfVote = noOfVote;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog(long blogID, User user, @NotBlank String content, LocalDate dateBlogCreated, int noOfView,
			int noOfVote) {
		super();
		this.blogID = blogID;
		this.user = user;
		this.content = content;
		this.dateBlogCreated = dateBlogCreated;
		this.noOfView = noOfView;
		this.noOfVote = noOfVote;
	}

	
	// Constructors, getters, and setters
}
