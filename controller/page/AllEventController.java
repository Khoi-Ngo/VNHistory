package com.swp.vnhistory.controller.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.repository.IEventsRepository;

@RestController
@RequestMapping("/allevents")
public class AllEventController {

	@Autowired
	IEventsRepository eventsRepository;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getAllEvents() {
		try {

			List<EventForm> listEventForm = new ArrayList<>();
			List<Event> listEvent = eventsRepository.findAll();
			if (listEvent != null) {
				for (Event i : listEvent) {
					EventForm form = new EventForm(i.getEventID(), i.getEventName(), i.getThumbnail(), i.getContent(),
							null);
					listEventForm.add(form);
				}

				return ResponseEntity.ok(listEventForm);

			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
			// TODO: handle exception
		}
	}

}
