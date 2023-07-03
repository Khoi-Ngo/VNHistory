package com.swp.vnhistory.service.impl;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.SavingRequestForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.SavedEvent;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IEventCommentRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.ISavedEventRepo;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class SavingService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	ISavedEventRepo savedEventRepo;

	public Object savingEvent(SavingRequestForm form) {
		if (form.getEventId() <= 0 || form.getEventId() == null) {
			return new ResponeMessage("no event found");
		}
		if (form.getUserId() <= 0 || form.getUserId() == null) {
			return new ResponeMessage("user not found");
		}

		User user = null;
		Event event = null;
		try {
			user = userRepository.getById(form.getUserId());
			if (user == null) {
				throw new EntityNotFoundException("User not found");
			}

			event = eventsRepository.getById(form.getEventId());
			if (event == null) {
				throw new EntityNotFoundException("Event not found");
			}
		} catch (EntityNotFoundException e) {
			// Handle the exception here
			return new ResponeMessage("User or event not found");
		} catch (Exception e) {
			// Handle other exceptions here
			return new ResponeMessage("An error occurred");
		}

		// Continue with the rest of your code

		SavedEvent savedEvent;
		try {
			savedEvent = savedEventRepo.findByUserAndEvent(user, event);

		} catch (EntityNotFoundException e) {
			// Handle the exception here
			return new ResponeMessage("Saved event not found");
		} catch (Exception e) {
			// Handle other exceptions here
			return new ResponeMessage("An error occurred");
		}

		if (savedEvent == null && userRepository.findById(form.getUserId()) != null
				&& eventsRepository.findById(form.getEventId()) != null) {
			savedEvent = new SavedEvent(user, event);
			savedEventRepo.save(savedEvent);
			return new ResponeMessage("saved already");
		} else if (savedEvent != null && userRepository.findById(form.getUserId()) != null
				&& eventsRepository.findById(form.getEventId()) != null) {
			// save roi -> delete
			savedEventRepo.delete(savedEvent);
			return new ResponeMessage("unsaved already");
//			return "asdsa";
		}
		return "ERROR";

	}

}
