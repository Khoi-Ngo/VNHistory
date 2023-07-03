package com.swp.vnhistory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.AnswerSheet;
import com.swp.vnhistory.dto.request.GetQuizForm;
import com.swp.vnhistory.dto.request.SavedChoice;
import com.swp.vnhistory.dto.respone.AfterDoQuizForm;
import com.swp.vnhistory.dto.respone.QuizResultForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.ReviewQuizForm;
import com.swp.vnhistory.model.Question;
import com.swp.vnhistory.model.QuestionResult;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.model.QuizResult;
import com.swp.vnhistory.model.Ranking;
import com.swp.vnhistory.model.TypeUser;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IQuestionRepo;
import com.swp.vnhistory.repository.IQuestionResultRepo;
import com.swp.vnhistory.repository.IQuizRepo;
import com.swp.vnhistory.repository.IQuizResultRepository;
import com.swp.vnhistory.repository.IRankingRepository;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class DoQuizService {

	@Autowired
	IQuestionResultRepo questionResultRepo;
	@Autowired
	IQuestionRepo questionRepo;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IQuizRepo quizRepo;
	@Autowired
	IQuizResultRepository quizResultRepository;
	@Autowired
	IRankingRepository rankingRepository;

	// get quiz
	public Object getDoQuiz(GetQuizForm form) {
		if (form == null) {
			return new ResponeMessage("FORM INVALID");
		} else if (form.getQuizId() == null) {
			return new ResponeMessage("NO QUIZ FOUND");
		} else if (form.getUserId() == null) {
			return new ResponeMessage("Login session fail");
		}

		else {
//			return questionResultRepo.getById(form.getQuizId());
			return questionRepo.findAllByQuiz_QuizID(form.getQuizId());
			// get quiz -> get all question
		}
	}

	// do quiz (none + full || co lam)
	// them het vao question result -> be check qua lai voi correct answer de insert
	// vao tbl quiz result
	public Object submitQuiz(AnswerSheet answersheet) {

		try {
			String userName = userRepository.getById(answersheet.getUserId()).getUsername();
		} catch (Exception e) {
			return new ResponeMessage("no user found");
			// TODO: handle exception
		}

		try {

			String quizName = quizRepo.getById(answersheet.getQuizId()).getQuizName();
		} catch (Exception e) {
			return new ResponeMessage("no quiz found");
			// TODO: handle exception
		}

		User user = userRepository.getById(answersheet.getUserId());
		Quiz quiz = quizRepo.getById(answersheet.getQuizId());

		int sum = 0;
		int max = 0;

		for (SavedChoice i : answersheet.getOptionsChosen()) {
			// luc nao cung chay full vi neu co ko dien ket qua thi no se de chosen == null
			// front end luon de du so luong cau

			max++;

			if (i.getOptionChosen() == null) {
				i.setOptionChosen("");
			}
			Question question = questionRepo.getById(i.getQuestionId());
			//// tra ra dap an
			// check -> sum
			if (question.getCorrectAnswer().equals(i.getOptionChosen().trim())) {
				sum++;
			}

			QuestionResult rs = new QuestionResult(user, quiz, question, i.getOptionChosen());
			questionResultRepo.save(rs);
		}

		QuizResult quizResult = new QuizResult(user, quiz, LocalDate.now(), sum);
		quizResultRepository.save(quizResult);

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		// check typeUser at here
		Ranking ranking = rankingRepository.getById(answersheet.getUserId());
		if (ranking == null) {// never this case
			// do nothing
			// start to do quiz start to have rank
		} else {
			// ranking != null
			// check -> update rank user
			if (ranking.getTotal_score() > 1000) {
//					user.setTypeUser(TypeUser.PLANTINUM.toString());
				// query update
				userRepository.updateTypeUserById(answersheet.getUserId(), TypeUser.PLANTINUM.toString());

			}
			if (ranking.getTotal_score() > 100 && ranking.getTotal_score() <= 1000) {
				// query update
				userRepository.updateTypeUserById(answersheet.getUserId(), TypeUser.GOLD.toString());
			}
			if (ranking.getTotal_score() > 50 && ranking.getTotal_score() <= 100) {
				// query update
				userRepository.updateTypeUserById(answersheet.getUserId(), TypeUser.SILVER.toString());
			}
			if (ranking.getTotal_score() <= 50) {
				// query update
				userRepository.updateTypeUserById(answersheet.getUserId(), TypeUser.BRONZE.toString());
			} else {
				// query update
				userRepository.updateTypeUserById(answersheet.getUserId(), TypeUser.NA.toString());
			}
		}

		return new AfterDoQuizForm(new QuizResultForm(quizResult.getQuiz().getQuizID(), quizResult.getResultID(),
				quizResult.getDate(), quizResult.getQuiz().getQuizName(), null, sum), max); // return lai question
																							// correct form || only
																							// quizres
	}

	public Object reviewQuiz(GetQuizForm form) { // userid va quizid
		if (form == null) {
			return new ResponeMessage("No review result found");
		}

		try {
			String userName = userRepository.getById(form.getUserId()).getUsername();
		} catch (Exception e) {
			return new ResponeMessage("no user found");
			// TODO: handle exception
		}

		try {

			String quizName = quizRepo.getById(form.getQuizId()).getQuizName();
		} catch (Exception e) {
			return new ResponeMessage("no quiz found");
			// TODO: handle exception
		}

		User user = userRepository.getById(form.getUserId());
		Quiz quiz = quizRepo.getById(form.getQuizId());
		// list question -> loop get by userid quizid questionid
		// return reivewquizform (question + question chosen)
		List<ReviewQuizForm> listquestionReview = new ArrayList<>();
		// tao ra list
		List<Question> listQuestion = questionRepo.findAllByQuiz_QuizID(form.getQuizId());
		for (Question i : listQuestion) {
			// get answer of userid
			QuestionResult qr = questionResultRepo.findByUserAndQuizAndQuestion(user, quiz, i);
			ReviewQuizForm review = new ReviewQuizForm(i, qr);
			listquestionReview.add(review);

		}
		return listquestionReview;

	}

}
