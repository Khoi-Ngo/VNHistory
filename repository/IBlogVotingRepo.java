package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.swp.vnhistory.dto.respone.ViewBlogVoting;
import com.swp.vnhistory.model.BlogVoting;

public interface IBlogVotingRepo extends JpaRepository<BlogVoting, Long> {

	// delete blog -> delete voting of blog

//    @Modifying
//    @Query("DELETE FROM BlogVoting bv WHERE bv.blog.blogId = :blogId")
//    void deleteByBlogBlogId(@Param("blogId") int blogId);

	// find by userId and blogid

	@Query("SELECT bv FROM BlogVoting bv WHERE bv.user.userId = :userId ")
	List<BlogVoting> findByUserId(@Param("userId") long userId);

//
	// view
//	@Query("SELECT bv FROM BlogVoting bv WHERE bv.blog.blogId = :blogId")
//	List<BlogVoting> findAllByBlogId(@Param("blogId") long blogId);
}
