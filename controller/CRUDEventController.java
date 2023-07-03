package com.swp.vnhistory.controller;

import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.CreateEventForm;
import com.swp.vnhistory.dto.request.DeleteEventForm;
import com.swp.vnhistory.dto.request.PreviewUpdateEventForm;
import com.swp.vnhistory.dto.request.SearchRequestForm;
import com.swp.vnhistory.dto.request.UpdateEventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.Province;
import com.swp.vnhistory.repository.ILocationRepo;
import com.swp.vnhistory.service.impl.EventServiceImpl;

@RestController
@RequestMapping("/myevents")
public class CRUDEventController {// RELATE TO TBL_EVENT_COMMENT

	@Autowired
	EventServiceImpl eventServiceImpl;
	@Autowired
	ILocationRepo locationRepo;

	// DELETE
	@DeleteMapping("/event/{eventId}")
	@ResponseBody // ?eventid =
	public ResponseEntity<?> delete(@PathVariable("eventId") int eventId) {
		if (eventId <= 0) {
			return ResponseEntity.ok(new ResponeMessage("invalid eventId"));
		} else {
			return ResponseEntity.ok(eventServiceImpl.deleteEvent(new Long(eventId)));
		}
	}

	// UPDATE => FORM
	@PutMapping("/event")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @RequestBody UpdateEventForm updateEventForm) {

		if (updateEventForm == null) {
			return ResponseEntity.ok(new ResponeMessage("CAN NOT UPDATE THE EVENT ARTICLE"));
		} else {
			return ResponseEntity.ok(eventServiceImpl.updateEvent(updateEventForm));
		}
	}

	@PostMapping("/event")
	@ResponseBody
	public ResponseEntity<?> previewUpdate(@Valid @RequestBody PreviewUpdateEventForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("CAN NOT PREVIEW THE EVENT ARTICLE"));
		} else {
			return ResponseEntity.ok(eventServiceImpl.previewEvent(form.getEventId()));
		}
	}

	// SEARCH BY EVENT_NAME
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> search(@Valid @RequestBody SearchRequestForm form) {
		if (form == null) {
			form = new SearchRequestForm();
			form.setKeyword("");
		}
		return ResponseEntity.ok(eventServiceImpl.getListEvents(form.getKeyword()));
	}

	// CREATE EVENT
	@PostMapping("/create/submit")
	@ResponseBody
	public ResponseEntity<?> createEvent(@Valid @RequestBody CreateEventForm createEventForm) {

		if (createEventForm == null) {
			return ResponseEntity.ok(new ResponeMessage("Fail to create an event article"));
		} else {
			return ResponseEntity.ok(eventServiceImpl.createEvent(createEventForm));
		}
	}

	@PostMapping("/create/preview")
	@ResponseBody
	public ResponseEntity<?> previewEvent(@Valid @RequestBody CreateEventForm createEventForm) {
		if (createEventForm == null) {
			return ResponseEntity.ok(new ResponeMessage("Fail to preview the event article"));
		} else {
			return ResponseEntity.ok(createEventForm);// return the form again => front end get data end show the
														// preview
		}
	}

}
