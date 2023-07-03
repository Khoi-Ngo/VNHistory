package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
//import com.swp.vnhistory.dto.respone.TopEventForm;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.repository.IEventCommentRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.ILocationRepo;

@Service
public class MapService {
	@Autowired
	ILocationRepo locationRepo;

	@Autowired
	IEventsRepository eventsRepository;

	public Object getOutstandingEvents(Long locationId) {
		if (locationId == null) {
			return new ResponeMessage("no location found");
		}
		// get location
		Location location = locationRepo.getById(locationId);
		if (location == null) {
			return new ResponeMessage("no location found");
		}

		List<Event> listTopEvent = eventsRepository.findByLocationIdAndOutstanding(locationId);
		// valid null
		if (listTopEvent == null) {
			return new ResponeMessage("no top event found");
		}
		List<EventForm> listTopEventForm = new ArrayList<>();
		for (Event i : listTopEvent) {
			EventForm form = new EventForm(i.getEventID(), i.getEventName(), "updating code backend", i.getContent(),
					i.getImage());

			listTopEventForm.add(form);
		}

		return listTopEventForm;

	}
}
