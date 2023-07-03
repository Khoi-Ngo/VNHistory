package com.swp.vnhistory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_blog_votings")
public class BlogVoting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blogVoteId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public BlogVoting() {
		super();
	}

	public BlogVoting(int blogVoteId, User user, Blog blog) {
		super();
		this.blogVoteId = blogVoteId;
		this.user = user;
		this.blog = blog;
	}

	public BlogVoting(User user, Blog blog) {
		super();
		this.user = user;
		this.blog = blog;
	}

	public long getBlogVoteId() {
		return blogVoteId;
	}

	public void setBlogVoteId(long blogVoteId) {
		this.blogVoteId = blogVoteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	// Constructors, getters, and setters
}