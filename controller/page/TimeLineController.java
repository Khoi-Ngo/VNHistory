package com.swp.vnhistory.controller.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.repository.IDynastyRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.service.impl.BlogServiceImpl;

@RestController
@RequestMapping("/timeline")
public class TimeLineController {

	@Autowired
	IDynastyRepo dynastyRepo;

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	BlogServiceImpl blogServiceImpl;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getTimeline() {
		try {
			return ResponseEntity.ok(dynastyRepo.findAll());

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something error"));
			// TODO: handle exception
		}
	}

	@GetMapping("/{dynastyId}")
	@ResponseBody
	public ResponseEntity<?> getTimeline(@PathVariable("dynastyId") long dynastyId) {
		try {
			List<Event> list = eventsRepository.findByDynastyIdAndOutstanding(dynastyId);
			List<EventForm> listForm = new ArrayList<>();
			for (Event i : list) {
				EventForm form = new EventForm(i.getEventID(), i.getEventName(), i.getThumbnail(),
						blogServiceImpl.getFirst15Words(i.getContent()), i.getImage());
				listForm.add(form);
			}

			return ResponseEntity.ok(listForm);

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Something error"));
			// TODO: handle exception
		}
	}

}
