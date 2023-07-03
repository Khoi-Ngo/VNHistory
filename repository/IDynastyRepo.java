package com.swp.vnhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.Dynasty;

//@Repository
public interface IDynastyRepo extends JpaRepository<Dynasty, Long> {
	@Query("SELECT d FROM Dynasty d WHERE d.dynastyName LIKE %:dynastyName%")
	Dynasty findByDynastyNameLike(@Param("dynastyName") String dynastyName);

}
