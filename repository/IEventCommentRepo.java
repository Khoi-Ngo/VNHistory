package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.EventComment;

//@Repository
public interface IEventCommentRepo extends JpaRepository<EventComment, Long> {

	@Transactional
	void deleteByEvent_EventID(long eventId);

	// Single Event page
	List<EventComment> findAllByEvent(Event event);

	@Transactional
	@Modifying
	@Query("UPDATE EventComment ec SET ec.content = :newContent WHERE ec.eventCommentID = :eventCommentId")
	void updateContentById(long eventCommentId, String newContent);

}
