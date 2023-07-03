package com.swp.vnhistory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_blog_comment_votings")
public class BlogCommentVoting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blogCommentVoteId;

	
	

	public BlogCommentVoting() {
		super();
	}

	public BlogCommentVoting(long blogCommentVoteId, User user, BlogComment blogComment) {
		super();
		this.blogCommentVoteId = blogCommentVoteId;
		this.user = user;
		this.blogComment = blogComment;
	}

	public long getBlogCommentVoteId() {
		return blogCommentVoteId;
	}

	public void setBlogCommentVoteId(long blogCommentVoteId) {
		this.blogCommentVoteId = blogCommentVoteId;
	}

	public BlogCommentVoting(User user, BlogComment blogComment) {
		super();
		this.user = user;
		this.blogComment = blogComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BlogComment getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(BlogComment blogComment) {
		this.blogComment = blogComment;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "blogComment_id")
	private BlogComment blogComment;

	// Constructors, getters, and setters
}
