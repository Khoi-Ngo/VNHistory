package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.respone.CreateEventDynastyForm;
import com.swp.vnhistory.dto.respone.CreateEventLocationForm;
import com.swp.vnhistory.dto.respone.PageEventCreate;
import com.swp.vnhistory.dto.respone.PageUpdateBlogForm;
import com.swp.vnhistory.dto.respone.PageUpdateEventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.repository.IDynastyRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.ILocationRepo;

@Service
public class PageCreateUpdateService {

	@Autowired
	ILocationRepo locationRepo;
	@Autowired
	IDynastyRepo dynastyRepo;

	@Autowired
	IEventsRepository eventsRepository;

	public Object GetPageCreateEvent() {
		// return cac list location va dynasty
		List<CreateEventDynastyForm> listDynastyForms = new ArrayList<>();

		for (Dynasty i : dynastyRepo.findAll()) {
			listDynastyForms.add(new CreateEventDynastyForm(i.getDynastyID(), i.getDynastyName()));
		}

		List<CreateEventLocationForm> listLocationForms = new ArrayList<>();

		for (Location i : locationRepo.findAll()) {
			listLocationForms.add(new CreateEventLocationForm(i.getLocationID(), i.getLocationName()));
		}
		PageEventCreate page = new PageEventCreate(listLocationForms, listDynastyForms);
		return page;

	}

//	public Object GetPageUpdateEvent(long eventId) {
//		try {
//			Event event = eventsRepository.getById(eventId);
//			PageUpdateEventForm form = new PageUpdateEventForm((PageEventCreate) GetPageCreateEvent(), eventId,
//					event.getEventName(), event.getContent(), event.isOustanding());
//
//			return form;
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return new ResponeMessage("Something wrong - service");
//		}
//	}

}
