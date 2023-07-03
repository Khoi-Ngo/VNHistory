package com.swp.vnhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.QuizResult;
import com.swp.vnhistory.model.SavedEvent;
import com.swp.vnhistory.model.User;

@Repository
public interface IQuizResultRepository extends JpaRepository<QuizResult, Long> {

	// count max number of months table has

	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date), '-', MONTH(date)) AS month\r\n" + "  FROM tbl_quiz_results\r\n"
			+ ") AS distinct_months;\r\n" + "", nativeQuery = true)
	int countDistinctMonths();

	// COUNT DO QUIZ MONTHLY
//	@Query(value = "SELECT count(*), year(date) as year, month(date) as month FROM swp03.tbl_quiz_results group by year(date), month(date);", nativeQuery = true)
//	List<Object[]> countByYearMonth();
	@Query(value = "SELECT count(*), year(date) as year, month(date) as month FROM swp03.tbl_quiz_results group by year(date), month(date) ORDER BY year(date) DESC, month(date) DESC;", nativeQuery = true)
	List<Object[]> countByYearMonth();


	@Query("SELECT COUNT(DISTINCT quizid) FROM QuizResult  WHERE user_id = :userId")
	int getTotalQuiz(long userId);
	
	
	List<QuizResult> findAllByUser(User user);
}
