package com.swp.vnhistory.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.Province;
import com.swp.vnhistory.repository.ILocationRepo;
import com.swp.vnhistory.service.impl.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

	@Autowired
	ILocationRepo locationRepo;
	// load all location

	@Autowired
	MapService mapService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> getMap() {


		return ResponseEntity.ok(locationRepo.findAll());
	}

	@GetMapping("/{locationId}")
	@ResponseBody
	public ResponseEntity<?> getTopEvent(@PathVariable("locationId") int locationId) {
		if (locationId <= 0) {
			return ResponseEntity.ok(new ResponeMessage("no location found"));
		} else {
			return ResponseEntity.ok(mapService.getOutstandingEvents(new Long(locationId)));
		}
	}

}
