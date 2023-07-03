package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.Blog;
import com.swp.vnhistory.model.BlogComment;

public interface IBlogCommentRepo extends JpaRepository<BlogComment, Long> {

	@Transactional
	// delete blog -> delete blog comment
	void deleteByBlogBlogID(long blogId);

	@Transactional
	void deleteByBlogCommentID(long blogCommentId);

//	void updateCommentByBlogCommentID(long blogCommentId, String comment);
	@Modifying
	@Transactional
	@Query("UPDATE BlogComment bc SET bc.comment = :comment WHERE bc.blogCommentID = :blogCommentId")
	void updateCommentByBlogCommentID(long blogCommentId, String comment);

	@Modifying
	@Transactional
	@Query("UPDATE BlogComment bc SET bc.noOfVote = bc.noOfVote + 1 WHERE bc.blogCommentID = :blogCommentId")
	void increaseVoteByBlogCommentID(long blogCommentId);

	@Modifying
	@Transactional
	@Query("UPDATE BlogComment bc SET bc.noOfVote = bc.noOfVote - 1 WHERE bc.blogCommentID = :blogCommentId")
	void decreaseVoteByBlogCommentID(long blogCommentId);

	// single blog

	@Query("SELECT bc FROM BlogComment bc WHERE bc.blog = :blog")
	List<BlogComment> findAllByBlog(@Param("blog") Blog blog);
}
