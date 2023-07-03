package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.User;

@Repository
public interface IAccountRepository extends JpaRepository<User, Long> {

	List<User> findByUsername(String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.username = :username, u.email = :email WHERE u.userId = :userId")
	void updateUser(@Param("userId") Long userId, @Param("username") String username, @Param("email") String email);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.username = :username, u.email = :email, u.role = :role WHERE u.userId = :userId")
	void updateUser(
	    @Param("userId") Long userId,
	    @Param("username") String username,
	    @Param("email") String email,
	    @Param("role") String role
	);

	
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password = :password WHERE u.userId = :userId")
	void updatePassword(@Param("userId") Long userId, @Param("password") String password);

	@Modifying
	@Query("DELETE FROM User u WHERE u.userId = :userId")
	void deleteUserById(@Param("userId") Long userId);

	List<User> findByUsernameContaining(String username);

	// DELETE
//	@Transactional
	@Modifying
	@Query("DELETE FROM User u WHERE u.username = :username")
	void deleteUserByUsername(@Param("username") String username);

	// count month
	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date), '-', MONTH(date)) AS month\r\n" + "  FROM tbl_users\r\n"
			+ ") AS distinct_months;\r\n" + "", nativeQuery = true)
	int countDistinctMonths();
//	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
//			+ "  SELECT DISTINCT CONCAT(YEAR(date_event_created), '-', MONTH(date_event_created)) AS month\r\n"
//			+ "  FROM tbl_events\r\n" + ") AS distinct_months;\r\n" + "", nativeQuery = true)

	// list no of user group by month
//	@Query("SELECT YEAR(u.date) AS year, MONTH(u.date) AS month, COUNT(u) AS count FROM User u GROUP BY YEAR(u.date), MONTH(u.date)")
//	List<Object[]> countUsersByYearAndMonth();
	@Query("SELECT YEAR(u.date) AS year, MONTH(u.date) AS month, COUNT(u) AS count FROM User u GROUP BY YEAR(u.date), MONTH(u.date) ORDER BY YEAR(u.date) DESC, MONTH(u.date) DESC ")
	List<Object[]> countUsersByYearAndMonth();

}
