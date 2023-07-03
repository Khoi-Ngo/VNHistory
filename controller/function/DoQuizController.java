package com.swp.vnhistory.controller.function;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.AnswerSheet;
import com.swp.vnhistory.dto.request.GetQuizForm;

import com.swp.vnhistory.dto.request.SavedChoice;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.service.impl.DoQuizService;

@RestController
@RequestMapping("/doQuiz")
public class DoQuizController {

	@Autowired
	DoQuizService doQuizService;

	// get -> xem de
	@PostMapping("/viewQuiz")
	@ResponseBody // can xem thu quiz id || event id
	public ResponseEntity<?> getQuiz(@Valid @RequestBody GetQuizForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Cannot take the quiz"));
		} else {
			// call question service -> get all
//			try {
				return ResponseEntity.ok(doQuizService.getDoQuiz(form));
//			} catch (Exception e) {
//				return ResponseEntity.notFound().build();
//				// TODO: handle exception
//			}
		}
	}

	// post -> nop bai
	@PostMapping("/submit")
	@ResponseBody
	public ResponseEntity<?> submitQuiz(@Valid @RequestBody AnswerSheet form) {
		if (form == null) {
			// cal service here -> ko dien dap an nao het

			// return 1 Response Message -> chuyen trang
			return ResponseEntity.ok(new ResponeMessage("Error at answersheet"));
		} else if (form.getQuizId() == null) {
			return ResponseEntity.ok(new ResponeMessage("invalid quizid, submit fail"));
		} else if (form.getUserId() == null) {
			return ResponseEntity.ok(new ResponeMessage("losing account session"));
		}

		else {
			if (form.getOptionsChosen() == null) {
				form.setOptionsChosen(new ArrayList<SavedChoice>());
			}

			try {
				return ResponseEntity.ok(doQuizService.submitQuiz(form));

			} catch (Exception e) {
				return ResponseEntity.ok(new ResponeMessage("Something wrong while submiting"));
				// TODO: handle exception
			}

		}
	}

	@PostMapping("/review")
	@ResponseBody
	public ResponseEntity<?> reviewQuiz(@Valid @RequestBody GetQuizForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("No quiz done found"));
		} else {
			// call service get question + question result
			try {
				return ResponseEntity.ok(doQuizService.reviewQuiz(form));
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
				// TODO: handle exception
			}

		}
	}

}
