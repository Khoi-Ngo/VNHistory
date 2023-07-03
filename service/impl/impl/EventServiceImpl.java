package com.swp.vnhistory.service.impl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swp.vnhistory.dto.request.CreateEventForm;
import com.swp.vnhistory.dto.request.UpdateEventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
//import com.swp.vnhistory.dto.respone.UpdateEventFormRes;
import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IDynastyRepo;
import com.swp.vnhistory.repository.IEventCommentRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.ILocationRepo;

@Service

public class EventServiceImpl {

	@Autowired
	ILocationRepo locationRepo;
	@Autowired
	IAccountRepository accountRepository;
	@Autowired
	IDynastyRepo dynastyRepo;
	@Autowired
	IEventsRepository eventsRepository;
	@Autowired
	IEventCommentRepo eventCommentRepo;

	// create event
	public Object createEvent(CreateEventForm form) {
		if (form != null) {
			try {
				// check Dynasty
				Dynasty dynasty = dynastyRepo.getById(form.getDynastyId());
				if (dynasty == null) {
					return new ResponeMessage("invalid dynasty");
				}

				Location location = locationRepo.getById(form.getLocationId());
				User user = accountRepository.getById(form.getUserId());

				Event event = new Event(dynasty, user, location, form.getEventName(), form.getContent(),
						form.getThumbnail(), form.getImage(), form.isOutstanding());
				eventsRepository.save(event);
				return new ResponeMessage("created event");
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponeMessage("something wrong while creating");

				// TODO: handle exception
			}
		} else {
			return new ResponeMessage("invalid form");
		}

	}

	// delete event
	public ResponeMessage deleteEvent(long eventId) {

		if (eventId <= 0) {
			return new ResponeMessage("Invalid eventId");
		} else {
			// delete table comment
			eventCommentRepo.deleteByEvent_EventID(eventId);
			// delete table event
			eventsRepository.deleteByEventID(eventId);
			return new ResponeMessage("deleted an event article");
		}
	}

	// update event
	// update event
	public Object updateEvent(UpdateEventForm form) {
		if (form == null) {
			return null;
		} else {
			try {
				Event event = eventsRepository.getById(form.getEventId());
				Dynasty dynasty = dynastyRepo.getById(form.getDynastyId());
				if (event != null) {
					if (form.getContent() == null) {
						form.setContent("");
					}

					if (form.getEventName() == null) {
						form.setEventName("");
					}

					if (dynasty == null) {
						return new ResponeMessage("invalid dynasty");
					}

					Location location = locationRepo.getById(form.getLocationId());
					eventsRepository.updateEvent(form.getEventId(), form.getEventName(), form.getContent(), dynasty,
							location, form.getThumbnail(), form.getImage());
					return new ResponeMessage("Updated successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponeMessage("Something went wrong while updating");
			}
		}
		return null;
	}

	public Event previewEvent(long eventId) {
		// list location va dynasty
		return eventsRepository.getById(eventId);
	}

	// search event
	public List<Event> getListEvents(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return eventsRepository.findByEventNameContaining(keyword);
	}
}
