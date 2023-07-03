package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.dto.request.SearchBlogForm;
import com.swp.vnhistory.model.Blog;

@Repository
public interface IBlogsRepository extends JpaRepository<Blog, Long> {

	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date_blog_created), '-', MONTH(date_blog_created)) AS month\r\n"
			+ "  FROM tbl_blogs\r\n" + ") AS distinct_months;\r\n" + "", nativeQuery = true)
	int countDistinctMonths();

//    @Query(value = "SELECT YEAR(date_blog_created) AS year, MONTH(date_blog_created) AS month, COUNT(*) AS count FROM tbl_blogs GROUP BY YEAR(date_blog_created), MONTH(date_blog_created) ", nativeQuery = true)
//    List<Object[]> countBlogsByYearAndMonth();

	@Query(value = "SELECT YEAR(date_blog_created) AS year, MONTH(date_blog_created) AS month, COUNT(*) AS count FROM tbl_blogs GROUP BY YEAR(date_blog_created), MONTH(date_blog_created) ORDER BY YEAR(date_blog_created) DESC, MONTH(date_blog_created) DESC", nativeQuery = true)
	List<Object[]> countBlogsByYearAndMonth();

	// CRUD

	// DELETE
	@Transactional
	void deleteByBlogID(long blogID);

	// UPDATE
	@Modifying
	@Query(value = "UPDATE Blog b SET b.content = :content WHERE b.blogID = :blogID")
	@Transactional
	Integer updateContentByBlogID(@Param("blogID") long blogID, @Param("content") String content);

	@Query("SELECT new com.swp.vnhistory.dto.request.SearchBlogForm(b.blogID, b.content) FROM Blog b WHERE b.content LIKE %:keyword%")
	List<SearchBlogForm> findBlogsByContentContainingKeyword(String keyword);

	@Query("SELECT b FROM Blog b WHERE b.hashtag LIKE %:hashtag%")
	List<Blog> findBlogsByHashtagContaining(String hashtag);

	// +-1 luot vote cho blog
	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.noOfVote = b.noOfVote - 1 WHERE b.blogID = :blogID")
	void decrementNoOfVoteByOne(@Param("blogID") long blogID);

	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.noOfVote = b.noOfVote + 1 WHERE b.blogID = :blogID")
	void incrementNoOfVoteByOne(@Param("blogID") long blogID);

	// + 1 luot view cho blog
	@Modifying
	@Transactional
	@Query("UPDATE Blog b SET b.noOfView = b.noOfView + 1 WHERE b.blogID = :blogID")
	void incrementNoOfViewByOne(@Param("blogID") long blogID);

	// homepage

	@Query("SELECT b FROM Blog b ORDER BY b.noOfVote DESC")
	List<Blog> findBlogsOrderByVotes();

	@Query("SELECT b FROM Blog b WHERE YEAR(b.dateBlogCreated) = YEAR(CURRENT_DATE()) AND MONTH(b.dateBlogCreated) = MONTH(CURRENT_DATE())")
	List<Blog> findBlogsCreatedInCurrentMonth();

}
