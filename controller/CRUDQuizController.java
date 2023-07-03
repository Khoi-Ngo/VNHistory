package com.swp.vnhistory.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.CreateQuestionForm;
import com.swp.vnhistory.dto.request.CreateQuizForm;
import com.swp.vnhistory.dto.request.DeleteListQuestion;
import com.swp.vnhistory.dto.request.DeleteQuizForm;
import com.swp.vnhistory.dto.request.QuizUpdateForm;
import com.swp.vnhistory.dto.request.SearchQuizForm;
import com.swp.vnhistory.dto.request.SearchRequestForm;
import com.swp.vnhistory.dto.request.UpdateQuestionForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.repository.IQuestionRepo;
import com.swp.vnhistory.repository.IQuizRepo;
import com.swp.vnhistory.service.impl.QuestionService;
import com.swp.vnhistory.service.impl.QuizServiceImpl;

@RestController
@RequestMapping("/quizList")
public class CRUDQuizController {

	@Autowired
	QuizServiceImpl quizServiceImpl;
	@Autowired
	IQuizRepo quizRepo;
	// danh sach quiz editor truy cap

	// create quiz
	@PostMapping("/quizcreate")
	@ResponseBody
	public ResponseEntity<?> createQuiz(@Valid @RequestBody CreateQuizForm form) {
		if (form == null) {
			return ResponseEntity.ok("FAIL TO CREATE QUIZ (FORM INVALID)");
		} else {
			try {
				return ResponseEntity.ok(quizServiceImpl.create(form));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}
		}
	}

	// delete quiz
	@DeleteMapping("/quiz/{quizId}") // ko can delete quiz Result vi do la thanh tich cua ca nhan -> delete all
	// questions || check null quizId tbl_question -> delete
	@ResponseBody
	public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") int quizId) {
		if (quizId <= 0) {
			return ResponseEntity.ok(new ResponeMessage("FAIL TO DELETE QUIZ"));
		} else {

			try {
				quizRepo.deleteById(new Long(quizId));
				return ResponseEntity.ok(new ResponeMessage("DELETED QUIZ ALREADY"));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}
		}
	}

	// search quiz
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> listQuiz(@Valid @RequestBody SearchRequestForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Error cannot no quizzes of user can found"));
		}
		// always have userId
		try {
			return ResponseEntity.ok(quizServiceImpl.search(form.getKeyword()));// list
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			// TODO: handle exception
		}

	}

//	 update quiz
	@PutMapping("/quiz/{quizId}")
	@ResponseBody
	public ResponseEntity<?> updateQuiz(@Valid @RequestBody QuizUpdateForm form, @PathVariable("quizId") int quizId) {
		if (quizId <= 0) {
			return ResponseEntity.notFound().build();
		}

		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage(" INVALID TO UPDATE QUIZ"));
		} else {
			// call service

			try {
				return ResponseEntity.ok(quizServiceImpl.update(form, new Long(quizId)));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}

		}
	}

	/*
	 * QQQQQQQQ UU UU EEEEEEEE SSSSSSSS TTTTTTTTT IIIIIII OOOOOOO NNNN NN QQ QQ UU
	 * UU EE SS TT II OO OO NNNNN NN QQ QQ UU UU EE SSSSSSSS TT II OO OO NN NN NN QQ
	 * QQ UU UU EEEEEEE SS TT II OO OO NN NNNN QQ QQ UU UU EE SSSSSSSS TT II OO OO
	 * NN NNN QQ QQ UUUUUU EE SS TT II OO OO NN NN QQQQQQQQ UU EEEEEEEE SSSSSSSS TT
	 * IIIIIII OOOOOOO NN NN
	 * 
	 */

	@Autowired
	IQuestionRepo questionRepo;
	@Autowired
	QuestionService questionService;

	// trong luc tao quiz sau khi dien form -> them cau hoi
	// create
	@PostMapping("questions/question")
	@ResponseBody
	public ResponseEntity<?> createQuestion(@Valid @RequestBody CreateQuestionForm form) {
		// nhan vao la danh sach cac cau hoi can duoc tao
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("form is null - controller alert"));
		} else {
			try {
				return ResponseEntity.ok(questionService.create(form));
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("fail while creating question"));
				// TODO: handle exception
			}
		}
	}

	// trong phan quan ly quiz -> click vao quiz -> search cac cau hoi co trong quiz
	// search || list question
	@GetMapping("/quiz/{quizId}/questions")
	@ResponseBody
	public ResponseEntity<?> searchQuestion(@PathVariable("quizId") int quizId) {
		SearchRequestForm form = new SearchRequestForm("");
		return ResponseEntity.ok(questionService.search(new Long(quizId), form.getKeyword()));

	}

	// trong quan ly quiz -> click vao quiz -> delete cac cau hoi co san
	// delete
	@DeleteMapping("/questions/question/{questionId}")
	@ResponseBody
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") int questionId) {
		if (questionId <= 0) {
			return ResponseEntity
					.ok(new ResponeMessage("cannot delete question - invalid questionid - controller alert"));
		} else {
			try {
				return ResponseEntity.ok(questionService.delete(new Long(questionId)));
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("cannot delete"));
				// TODO: handle exception
			}
		}
	}

	@DeleteMapping("/questions")
	@ResponseBody
	public ResponseEntity<?> deleteListQuestion(@Valid @RequestBody DeleteListQuestion form) {
		if (form == null || form.getListQuestionId() == null) {
			return ResponseEntity.ok(new ResponeMessage("no question to delete - controller alert"));
		}
		if (form.getListQuestionId().isEmpty()) {
			return ResponseEntity.ok(new ResponeMessage("empty list question to delete - controller alert"));
		}
		try {
			return ResponseEntity.ok(questionService.delete(form.getListQuestionId()));
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponeMessage("Some questions could not be deleted"));
			// TODO: handle exception
		}
	}

	// tuong tu trong phan quan ly quiz -> click vao quiz
	// update
	@PutMapping("/questions/question")
	@ResponseBody
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody UpdateQuestionForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("invalid form to update question - controller alert"));
		} else {
			try {
				return ResponseEntity.ok(questionService.update(form));
			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("could not update"));
				// TODO: handle exception
			}
		}
	}

}
