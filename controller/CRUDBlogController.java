package com.swp.vnhistory.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swp.vnhistory.dto.request.CreateBlogForm;
import com.swp.vnhistory.dto.request.DeleteBlogForm;
import com.swp.vnhistory.dto.request.DeleteEventForm;
import com.swp.vnhistory.dto.request.PreviewUpdateBlogForm;
import com.swp.vnhistory.dto.request.PreviewUpdateEventForm;
import com.swp.vnhistory.dto.request.SearchRequestForm;
import com.swp.vnhistory.dto.request.UpdateBlogForm;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.repository.IBlogsRepository;
import com.swp.vnhistory.service.impl.BlogServiceImpl;

@RestController
@RequestMapping("/myblogs")
public class CRUDBlogController {

	@Autowired
	BlogServiceImpl blogServiceImpl;

	// delete
	@DeleteMapping("/blog")
	@ResponseBody // ?eventid =
	public ResponseEntity<?> delete(@Valid @RequestBody DeleteBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("invalid blogid"));
		} else {
			return ResponseEntity.ok(blogServiceImpl.deleteBlog(form.getBlogId()));
		}
	}

	// update + preview
	@PutMapping("/blog")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @RequestBody UpdateBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("CAN NOT UPDATE THE BLOG"));
		} else {
			return ResponseEntity.ok(blogServiceImpl.update(form));
		}
	}

	@GetMapping("/blog")
	@ResponseBody
	public ResponseEntity<?> preview(@Valid @RequestBody PreviewUpdateBlogForm form) {
		if (form == null || form.getBlogId() <= 0) {
			return ResponseEntity.ok(new ResponeMessage("INVALID PREVIEW FORM REQUEST"));
		} else {
			return ResponseEntity.ok(blogServiceImpl.previewBlog(form.getBlogId()));
		}
	}

	// search
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> search(@Valid @RequestBody SearchRequestForm form) {
		if (form == null) {
			form = new SearchRequestForm("");
		}
		if (form.getKeyword() == null) {
			form.setKeyword("");
		}
		return ResponseEntity.ok(blogServiceImpl.search(form.getKeyword()));
	}

	// search by hashtag
	@PostMapping("/searchhashtag")
	@ResponseBody
	public ResponseEntity<?> searchByHashtag(@Valid @RequestBody SearchRequestForm form) {
		if (form == null) {
			form = new SearchRequestForm("");
		}
		if (form.getKeyword() == null) {
			form.setKeyword("");
		}
		return ResponseEntity.ok(blogServiceImpl.searchTag(form.getKeyword()));
	}

	// create
	@PostMapping("/create/submit")
	@ResponseBody
	public ResponseEntity<?> createBlog(@Valid @RequestBody CreateBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Fail to create an event article"));
		} else {
			return ResponseEntity.ok(blogServiceImpl.createBlog(form));
		}

	}

	@PostMapping("/create/preview")
	@ResponseBody
	public ResponseEntity<?> previewBlog(@Valid @RequestBody CreateBlogForm form) {
		if (form == null) {
			return ResponseEntity.ok(new ResponeMessage("Fail to preview the event article"));
		} else {
			return ResponseEntity.ok(form);// return the form again => front end get data end show the preview
		}
	}
}
