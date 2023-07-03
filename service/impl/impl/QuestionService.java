package com.swp.vnhistory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.CreateQuestionForm;
import com.swp.vnhistory.dto.request.UpdateQuestionForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Question;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IQuestionRepo;
import com.swp.vnhistory.repository.IQuizRepo;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class QuestionService {

	@Autowired
	IQuestionRepo questionRepo;
	@Autowired
	IQuizRepo quizRepo;

	@Autowired
	IUserRepository userRepository;

	// create question

	public Object create(CreateQuestionForm form) {
		if (form == null) {
			return new ResponeMessage("invalid form to create question");
		} else {

			// check quizId
			if (form.getQuizId() == null) {
				return new ResponeMessage("no quiz found");
			}

			// check null question
			if (form.getQuestion() == null || form.getQuestion().trim().equals("")) {
				return new ResponeMessage("empty question");
			}
			// check null correct answer
			if (form.getCorrectChoice() == null) {
				return new ResponeMessage("empty correct answer");
			}

			// check null option -> maybe no need if can insert null value
			if (form.getOption1() == null) {
				form.setOption1("");
			}
			if (form.getOption2() == null) {
				form.setOption2("");
			}
			if (form.getOption3() == null) {
				form.setOption3("");
			}
			if (form.getOption4() == null) {
				form.setOption4("");
			}

			Quiz quiz;
			try {
				quiz = quizRepo.getById(form.getQuizId());
				String user = userRepository.getById(quiz.getUser().getUserId()).getUsername();
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}

			try {

				Question question = new Question(quiz, form.getQuestion(), form.getOption1(), form.getOption2(),
						form.getOption3(), form.getOption4(), form.getCorrectChoice());
//				return questionRepo.save(question);
				return new ResponeMessage("created question");
			} catch (Exception e) {
				return new ResponeMessage("fail while creating question");
				// TODO: handle exception
			}
		}
	}

	// update question
	public Object update(UpdateQuestionForm form) { // return update question
		if (form == null) {
			return new ResponeMessage("invalid form to update question");
		} else {
			// update option || question -> if null -> set old value
			Question question = questionRepo.getById(form.getQuestionId());
			if (form.getCorrectChoice() == null) {
				form.setCorrectChoice(question.getCorrectAnswer());
			}
			if (form.getOption1() == null) {
				form.setOption1(question.getOption1());
			}
			if (form.getOption2() == null) {
				form.setOption2(question.getOption2());
			}
			if (form.getOption3() == null) {
				form.setOption3(question.getOption3());
			}
			if (form.getOption4() == null) {
				form.setOption4(question.getOption4());
			}
			if (form.getQuestion() == null || form.getQuestion().trim().equals("")) {
				form.setQuestion(question.getQuestion());
			}

			try {
				question = questionRepo.getById(form.getQuestionId());
				String user = userRepository.getById(question.getQuiz().getUser().getUserId()).getUsername();
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}

			questionRepo.updateQuestionById(form.getQuestionId(), form.getOption1(), form.getOption2(),
					form.getOption3(), form.getOption4(), form.getCorrectChoice(), form.getQuestion());
			return new ResponeMessage("updated question done");
		}
	}

	// search question by question
	public Object search(Long quizId, String question) {
		// nhan question va return select
		if (question == null) {
			question = "";
		}
		return questionRepo.findAllQuestionFormsByQuizIdAndKeyword(quizId, question);
	}

	// delete question
	public Object delete(Long questionId) {
		if (questionId == null) {
			return new ResponeMessage("no question found");

		} else {
			questionRepo.deleteById(questionId);
			return new ResponeMessage("deleted question already");
		}
	}

	// delete list
	public Object delete(List<Long> listQuestionId) {
		if (listQuestionId == null) {
			return new ResponeMessage("no question foudn");
		} else if (listQuestionId.isEmpty()) {
			return new ResponeMessage("no question to delete");
		} else {
			for (Long i : listQuestionId) {
				questionRepo.deleteById(i);
			}
			return new ResponeMessage("deleted all question selected");
		}
	}

}
