package com.swp.vnhistory.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.BlogComment;
import com.swp.vnhistory.model.BlogCommentVoting;
import com.swp.vnhistory.model.User;

@Repository
public interface IBlogCommentVotingRepo extends JpaRepository<BlogCommentVoting, Long> {

	// delete comment of blog -> delete comment voting

	// delete by blog_comment_id
	@Transactional
	void deleteByBlogComment_BlogCommentID(long blogCommentId);

	// find by userid
	BlogCommentVoting findByUser_UserId(long userId);

	// delete by blogcommentid and userid
	@Modifying
	@Transactional
	@Query("DELETE FROM BlogCommentVoting bcv WHERE bcv.user.userId = :userId AND bcv.blogComment.blogCommentID = :blogCommentId")
	void deleteByUserIdAndBlogCommentId(@Param("userId") long userId, @Param("blogCommentId") long blogCommentId);

	BlogCommentVoting findByUserAndBlogComment(User user, BlogComment blogComment);

}
