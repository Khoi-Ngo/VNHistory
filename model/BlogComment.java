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
@Table(name = "tbl_blog_comments")
public class BlogComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long blogCommentID;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public long getBlogCommentID() {
		return blogCommentID;
	}

	public void setBlogCommentID(long blogCommentID) {
		this.blogCommentID = blogCommentID;
	}

	public BlogComment(User user, Blog blog, @NotBlank String comment, LocalDate commentDate, int noOfVote) {
		super();
		this.user = user;
		this.blog = blog;
		this.comment = comment;
		this.commentDate = commentDate;
		this.noOfVote = noOfVote;
	}

	public BlogComment() {
		// default constructor
	}

	public BlogComment(long blogCommentID, User user, Blog blog, @NotBlank String comment, LocalDate commentDate,
			int noOfVote) {
		super();
		this.blogCommentID = blogCommentID;
		this.user = user;
		this.blog = blog;
		this.comment = comment;
		this.commentDate = commentDate;
		this.noOfVote = noOfVote;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}

	public int getNoOfVote() {
		return noOfVote;
	}

	public void setNoOfVote(int noOfVote) {
		this.noOfVote = noOfVote;
	}

	@ManyToOne
	@JoinColumn(name = "blogID")
	private Blog blog;

	@NotBlank
	@Lob
	private String comment;

	private LocalDate commentDate;

	private int noOfVote;

	// Constructors, getters, and setters
}
