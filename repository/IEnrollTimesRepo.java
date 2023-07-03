package com.swp.vnhistory.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.EnrollTime;

@Repository
public interface IEnrollTimesRepo extends JpaRepository<EnrollTime, Long> {

//	@Query(value = "SELECT SUM(times) AS total_sum, YEAR(date) AS year, MONTH(date) AS month\r\n"
//			+ "FROM tbl_enroll_times\r\n" + "GROUP BY YEAR(date), MONTH(date)\r\n"
//			+ "ORDER BY YEAR(date), MONTH(date);", nativeQuery = true)
//	List<Object[]> totalEnrollByMonths();
	@Query(value = "SELECT SUM(times) AS total_sum, YEAR(date) AS year, MONTH(date) AS month "
	        + "FROM tbl_enroll_times "
	        + "GROUP BY YEAR(date), MONTH(date) "
	        + "ORDER BY YEAR(date) DESC, MONTH(date) DESC", nativeQuery = true)
	List<Object[]> totalEnrollByMonths();


	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date), '-', MONTH(date)) AS month\r\n" + "  FROM tbl_enroll_times\r\n"
			+ ") AS distinct_months;\r\n" + "", nativeQuery = true)
	int countDistinctMonths();

	@Query(value = "SELECT * FROM tbl_enroll_times e WHERE e.date = CURDATE();\r\n"
			+ "", nativeQuery = true)
	List<Object[]> countByDate();

	@Transactional
    @Modifying
    @Query(value = "UPDATE tbl_enroll_times e SET e.times = e.times + 1 WHERE e.date = curdate() AND e.enroll_times_id > 0;", nativeQuery = true)
    void incrementTimesByDate();
}
