package com.swp.vnhistory.controller.function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.SavingRequestForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.service.impl.SavingService;

@RestController
public class Saving {

	//save hoac save lan 2 thi xoa
	@Autowired
	SavingService savingService;
	
	@PostMapping("/saveEvent")
	@ResponseBody
	public ResponseEntity<?> saveEvent(@Valid @RequestBody SavingRequestForm form) {
		if(form == null) {
			return ResponseEntity.ok(new ResponeMessage("fail to save"));
		}
		return ResponseEntity.ok(savingService.savingEvent(form));
	}
}
