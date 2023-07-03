package com.swp.vnhistory.repository;

import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IQuizRepo extends JpaRepository<Quiz, Long> {

	/**
	 * Finds quizzes by quiz name containing and user ID.
	 *
	 * @param quizName the partial or full quiz name to search for
	 * @param userId   the ID of the user
	 * @return a list of quizzes matching the criteria
	 */

	List<Quiz> findAllByQuizNameContaining(String quizName);

	@Transactional
	void deleteByQuizID(long quizId);

	// Update
	@Transactional
	@Modifying
	@Query("UPDATE Quiz q SET q.quizName = :quizName WHERE q.quizID = :quizID")
	void updateQuizNameByQuizID(long quizID, String quizName);

	// Count the distinct months in which quizzes were created
	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date_quiz_created), '-', MONTH(date_quiz_created)) AS month\r\n"
			+ "  FROM tbl_quizzes\r\n" + ") AS distinct_months;\r\n", nativeQuery = true)
	long countDistinctMonths();
//	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
//			+ "  SELECT DISTINCT CONCAT(YEAR(date_event_created), '-', MONTH(date_event_created)) AS month\r\n"
//			+ "  FROM tbl_events\r\n" + ") AS distinct_months;\r\n" + "", nativeQuery = true)
//	long countDistinctMonths();

	// quiz duoc tao ra trong thang theo user
	@Query(value = "SELECT YEAR(q.dateQuizCreated) AS year, MONTH(q.dateQuizCreated) AS month, COUNT(*) AS count FROM Quiz q GROUP BY YEAR(q.dateQuizCreated), MONTH(q.dateQuizCreated) ORDER BY YEAR(q.dateQuizCreated) DESC, MONTH(q.dateQuizCreated) DESC")
	List<Object[]> countQuizzesByYearAndMonthByEditor();

}
