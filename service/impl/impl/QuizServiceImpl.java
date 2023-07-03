package com.swp.vnhistory.service.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.CreateQuizForm;
import com.swp.vnhistory.dto.request.QuizUpdateForm;
import com.swp.vnhistory.dto.respone.AfterUpdateQuizForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Event;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.IQuizRepo;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class QuizServiceImpl {

	@Autowired
	IQuizRepo quizRepo;
	@Autowired
	IEventsRepository eventsRepository;
	@Autowired
	IUserRepository userRepository;

	// search
	public List<Quiz> search(String keyword) {
		if (keyword == null) {
			keyword = "";
		}

		return quizRepo.findAllByQuizNameContaining(keyword);
	}

	// update
	public Object update(QuizUpdateForm form, Long quizId) {
		if (form == null) {
			return new ResponeMessage(" INVALID");
		} else {
			if (form.getUpdateQuizName().equals("")) {
				// set old value
				Quiz quiz = quizRepo.getById(quizId);
				form.setUpdateQuizName(quiz.getQuizName());
			}
			quizRepo.updateQuizNameByQuizID(quizId, form.getUpdateQuizName());
			return new ResponeMessage("Updated quizname");
		}
	}

	// delete
	public ResponeMessage delete(Long quizId) {
		if (quizId == null) {
			return new ResponeMessage("CAN NOT DEFINE QUIZ TO DELETE");
		} else {
			quizRepo.deleteById(quizId);
			return new ResponeMessage("DELETED A QUIZ ALREADY");
		}
	}

	// create
	public Object create(CreateQuizForm form) {
		if (form == null) {
			throw new IllegalArgumentException("Form is null.");
		}

		Long eventId = new Long(form.getEventId());
		Event event = eventsRepository.getById(eventId);
		if (event == null) {
			throw new IllegalArgumentException("Event not found.");
		}
		Long userId = new Long(form.getUserId());
		User user = userRepository.getById(userId);
		if (user == null) {
			throw new IllegalArgumentException("User not found.");
		}

		String quizName = form.getQuizName();
		LocalDate date = LocalDate.now();

		Quiz quiz = new Quiz(user, quizName, date, event);
		quizRepo.save(quiz);
		// Response error but save good
		return new ResponeMessage("created quiz");
	}

}
