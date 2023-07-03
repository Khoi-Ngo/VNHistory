package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.ChangeProfileReq;
import com.swp.vnhistory.dto.respone.EventForm;
import com.swp.vnhistory.dto.respone.QuizResultForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.SavedEventForm;
import com.swp.vnhistory.dto.respone.UserForm;
import com.swp.vnhistory.dto.respone.ViewedEventForm;
import com.swp.vnhistory.model.QuizResult;
import com.swp.vnhistory.model.SavedEvent;
import com.swp.vnhistory.model.ViewedEvent;
import com.swp.vnhistory.repository.IQuizResultRepository;
import com.swp.vnhistory.repository.ISavedEventRepo;
import com.swp.vnhistory.repository.IUserRepository;
import com.swp.vnhistory.repository.IViewedEventRepo;

@Service
public class MemberProfileService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IViewedEventRepo viewedEventRepo;

	@Autowired
	ISavedEventRepo savedEventRepo;

	@Autowired
	IQuizResultRepository quizResultRepository;

	@Autowired
	BlogServiceImpl blogServiceImpl;

	public Object getMemberProfile(ChangeProfileReq userIdForm) {
		try {
			Optional<UserForm> optionalUserForm = userRepository.findUserFormById(userIdForm.getUserId());

			if (optionalUserForm.isPresent()) {
				UserForm userForm = optionalUserForm.get();
				// query to return list eventform
				return userForm;

			} else {
				return null; // 404 if user not found

			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponeMessage("Something wrong");
		}
	}

	public Object getReadEvent(ChangeProfileReq userIdForm) {
		try {
//			List<EventForm> listViewedEvent = new ArrayList<>();

			List<ViewedEvent> listViewedEvent = viewedEventRepo
					.findAllByUser(userRepository.getById(userIdForm.getUserId()));// mapper

			List<ViewedEventForm> listForm = new ArrayList<>();
			for (ViewedEvent i : listViewedEvent) {
				listForm.add(new ViewedEventForm(i.getViewID(), i.getEvent().getEventID(), i.getDateRead(),
						i.getEvent().getEventName(), blogServiceImpl.getFirst15Words(i.getEvent().getContent()),
						i.getEvent().getThumbnail()));
			}
			return listForm;

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponeMessage("Something wrong");
		}
	}

	public Object getSavedEvent(ChangeProfileReq userIdForm) {
		try {
//			List<EventForm> listViewedEvent = new ArrayList<>();

			List<SavedEvent> listSavedEvent = savedEventRepo
					.findAllByUser(userRepository.getById(userIdForm.getUserId()));// mapper

			List<SavedEventForm> listForm = new ArrayList<>();
			for (SavedEvent i : listSavedEvent) {
				listForm.add(new SavedEventForm(i.getSaveID(), i.getEvent().getEventID(), i.getEvent().getEventName(),
						i.getDateSave(), i.getEvent().getThumbnail(),
						blogServiceImpl.getFirst15Words(i.getEvent().getContent())));
			}
			return listForm;

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponeMessage("Something wrong");
		}
	}

	public Object getQuizDone(ChangeProfileReq userIdForm) {
		try {
			List<QuizResultForm> listQuizDone = new ArrayList<>();
			List<QuizResult> list = quizResultRepository.findAllByUser(userRepository.getById(userIdForm.getUserId()));
			for (QuizResult i : list) {
				listQuizDone.add(new QuizResultForm(i.getQuiz().getQuizID(), i.getResultID(), i.getDate(),
						i.getQuiz().getQuizName(), i.getQuiz().getEvent().getThumbnail(), i.getResult()));
			}
			return listQuizDone;

		} catch (Exception e) {
			return new ResponeMessage("Something wrong here");
			// TODO: handle exception
		}

	}
}
