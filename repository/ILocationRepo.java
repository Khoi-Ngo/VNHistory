package com.swp.vnhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.Location;

@Repository
public interface ILocationRepo extends JpaRepository<Location, Long>{

}
