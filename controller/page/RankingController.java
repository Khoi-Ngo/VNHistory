package com.swp.vnhistory.controller.page;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.SearchRequestForm;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.service.impl.AccountServiceImpl;
import com.swp.vnhistory.service.impl.RankingServiceImpl;

@RestController
@RequestMapping("/ranking")
public class RankingController {

	@Autowired
	RankingServiceImpl rankingServiceImpl;
	@Autowired
	IAccountRepository accountRepository;
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> RankingsPage(@Valid @RequestBody SearchRequestForm searchRequestForm){
		if(searchRequestForm == null) {
			searchRequestForm = new SearchRequestForm();
			searchRequestForm.setKeyword("");
			
		}
		//call service
		return ResponseEntity.ok(rankingServiceImpl.getTheRankingTable(searchRequestForm.getKeyword()));
	}
	
}
