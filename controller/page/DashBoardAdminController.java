package com.swp.vnhistory.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.repository.IEnrollTimesRepo;
import com.swp.vnhistory.repository.IQuizResultRepository;
import com.swp.vnhistory.service.impl.AccountServiceImpl;
import com.swp.vnhistory.service.impl.DashBoardAdminServiceImpl;

@RestController
@RequestMapping("/admin/dashboard")
public class DashBoardAdminController {

	@Autowired
	DashBoardAdminServiceImpl dashBoardAdminServiceImpl;
	@Autowired
	AccountServiceImpl accountServiceImpl;

	@Autowired
	IAccountRepository accountRepository;

	
	@GetMapping //overall dashboard
	@ResponseBody
	public ResponseEntity<?> getOveralDashBoard(){
		//default
		//trong thang -> noOfMonth = 1
		//callservice ->
		return ResponseEntity.ok(dashBoardAdminServiceImpl.GetOveralDashBoard());
		
	}
	
	
	// thanh vien moi theo cac thang
	@PostMapping("/newAccs/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getNewAccs(@PathVariable("noOfMonths") int noOfMonths) {
	    return ResponseEntity.ok(dashBoardAdminServiceImpl.getNewAccs(noOfMonths));
	}

	// bai viet theo cac thang
	@PostMapping("/newEvents/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getNewEvents(@PathVariable("noOfMonths") int noOfMonths) {
	    return ResponseEntity.ok(dashBoardAdminServiceImpl.getNewEvents(noOfMonths));
	}


	@Autowired
	IBlogsRepository blogsRepository;

	// blog theo cac thang
	@PostMapping("/newBlogs/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getNewBlogs(@PathVariable("noOfMonths") int noOfMonths) {
	    return ResponseEntity.ok(dashBoardAdminServiceImpl.getNewBlogs(noOfMonths));
	}


	// luot dang nhap
	@PostMapping("/enroll/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getEnrollTimes(@PathVariable("noOfMonths") int noOfMonths) {
	    return ResponseEntity.ok(dashBoardAdminServiceImpl.getEnrollTimes(noOfMonths));
	}

	@PostMapping("/doquiz/{noOfMonths}")
	@ResponseBody
	public ResponseEntity<?> getDoQuiz(@PathVariable("noOfMonths") int noOfMonths) {
	    return ResponseEntity.ok(dashBoardAdminServiceImpl.getDoQuiz(noOfMonths));
	}


}
