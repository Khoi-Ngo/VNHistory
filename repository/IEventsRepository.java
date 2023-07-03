package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.dto.respone.EventForm;
//import com.swp.vnhistory.dto.respone.TopEventForm;
import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.User;

//@Repository
public interface IEventsRepository extends JpaRepository<Event, Long> {

//DASHBOARD
	@Query(value = "SELECT count(*) AS total_sum\r\n" + "FROM (\r\n"
			+ "  SELECT DISTINCT CONCAT(YEAR(date_event_created), '-', MONTH(date_event_created)) AS month\r\n"
			+ "  FROM tbl_events\r\n" + ") AS distinct_months;\r\n" + "", nativeQuery = true)
	long countDistinctMonths();

//	@Query(value = "SELECT YEAR(date_event_created) AS year, MONTH(date_event_created) AS month, COUNT(*) AS count FROM tbl_events GROUP BY YEAR(date_event_created), MONTH(date_event_created)", nativeQuery = true)
//	List<Object[]> countEventsByYearAndMonth();

	@Query(value = "SELECT YEAR(date_event_created) AS year, MONTH(date_event_created) AS month, COUNT(*) AS count FROM tbl_events GROUP BY YEAR(date_event_created), MONTH(date_event_created) ORDER BY YEAR(date_event_created) DESC, MONTH(date_event_created) DESC", nativeQuery = true)
	List<Object[]> countEventsByYearAndMonth();

	@Query(value = "SELECT YEAR(e.date_event_created) AS year, MONTH(e.date_event_created) AS month, COUNT(*) AS count FROM Event e  GROUP BY YEAR(e.date_event_created), MONTH(e.date_event_created) ORDER BY YEAR(e.date_event_created) DESC, MONTH(e.date_event_created) DESC")
	List<Object[]> countEventsByYearAndMonthByEditor();

	@Query(value = "SELECT YEAR(e.date_event_created) AS year, MONTH(e.date_event_created) AS month, e.location.locationID,  COUNT(*) AS count FROM Event e GROUP BY YEAR(e.date_event_created), MONTH(e.date_event_created), e.location.locationID ORDER BY YEAR(e.date_event_created) DESC, MONTH(e.date_event_created) DESC")
	List<Object[]> countEventsByYearAndMonthByEditorByLocation();

	@Query(value = "SELECT YEAR(e.date_event_created) AS year, MONTH(e.date_event_created) AS month, e.dynasty.dynastyID,  COUNT(*) AS count FROM Event e  GROUP BY YEAR(e.date_event_created), MONTH(e.date_event_created), e.dynasty.dynastyID ORDER BY YEAR(e.date_event_created) DESC, MONTH(e.date_event_created) DESC")
	List<Object[]> countEventsByYearAndMonthByEditorByDynasty();

	// CRUD
	// delete
	@Transactional
	void deleteByEventID(long eventId);

	// update
	@Modifying
	@Transactional
	@Query("UPDATE Event e SET e.eventName = :eventName, e.content = :content, e.dynasty = :dynasty, e.location = :location, e.thumbnail = :thumbnail, e.image = :image WHERE e.eventID = :eventId")
	void updateEvent(@Param("eventId") long eventId, @Param("eventName") String eventName,
			@Param("content") String content, @Param("dynasty") Dynasty dynasty, @Param("location") Location location,
			@Param("thumbnail") String thumbnail, @Param("image") String image);

	// search
	List<Event> findByEventNameContaining(String eventName);

	// map outstanding events
	@Query("SELECT e FROM Event e WHERE e.location.locationID = :locationId AND e.oustanding = true")
	List<Event> findByLocationIdAndOutstanding(@Param("locationId") long locationId);

	// timeline outstanding
	@Query("SELECT e FROM Event e WHERE e.dynasty.dynastyID = :dynastyId AND e.oustanding = true")
	List<Event> findByDynastyIdAndOutstanding(@Param("dynastyId") long dynastyId);

	// homepage

	@Query("SELECT new com.swp.vnhistory.dto.respone.EventForm(e.eventID, e.eventName, e.thumbnail, e.content, e.image) FROM Event e WHERE FUNCTION('YEAR', e.date_event_created) = FUNCTION('YEAR', CURRENT_DATE) AND FUNCTION('MONTH', e.date_event_created) = FUNCTION('MONTH', CURRENT_DATE)")
	List<EventForm> findEventFormsCreatedInCurrentMonth();

//	List<Event> findByOustandingIsTrue();
	@Query("SELECT new com.swp.vnhistory.dto.respone.EventForm(e.eventID, e.eventName, e.thumbnail, e.content, e.image) FROM Event e WHERE e.oustanding = true")
	List<EventForm> findOutstandingEvents();

}
