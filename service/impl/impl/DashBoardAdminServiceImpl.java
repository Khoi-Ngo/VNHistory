package com.swp.vnhistory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.respone.NoOfAccMonthForm;
import com.swp.vnhistory.dto.respone.NoOfBlogForm;
import com.swp.vnhistory.dto.respone.NoOfDoQuizForm;
import com.swp.vnhistory.dto.respone.NoOfEnrollForm;
import com.swp.vnhistory.dto.respone.NoOfEventForm;
import com.swp.vnhistory.dto.respone.OverallDashboard;
import com.swp.vnhistory.dto.respone.ResponeMessage;
import com.swp.vnhistory.dto.respone.TakingQuizTimesForm;
import com.swp.vnhistory.model.EnrollTime;
import com.swp.vnhistory.model.RoleName;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IBlogsRepository;
//import com.swp.vnhistory.dto.respone.EnrollTimesResponseForm;
import com.swp.vnhistory.repository.IEnrollTimesRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.IQuizResultRepository;
import com.swp.vnhistory.security.userpincal.UserPrinciple;
//import com.swp.vnhistory.repository.IQuizResultRepository;

@Service
public class DashBoardAdminServiceImpl {
	@Autowired
	IEnrollTimesRepo enrollTimesRepo;

	@Autowired
	IAccountRepository accountRepository;

	@Autowired
	IBlogsRepository blogsRepository;

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	IQuizResultRepository quizResultRepository;

	public Object getNewAccs(int noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths db ->
			long maxOfMonths = accountRepository.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = (int) maxOfMonths;
			}
			List<Object[]> result = accountRepository.countUsersByYearAndMonth();
			List<NoOfAccMonthForm> formList = new ArrayList<>();
			// it nhat la co 1
			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int count = ((Number) row[2]).intValue();
				NoOfAccMonthForm form = new NoOfAccMonthForm(count, month, year);

				formList.add(form);
				numberToShow++;

			}

			return formList;
		}
	}

	public Object getNewBlogs(int noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths db ->
			long maxOfMonths = blogsRepository.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = (int) maxOfMonths;
			}
			List<Object[]> result = blogsRepository.countBlogsByYearAndMonth();
			List<NoOfBlogForm> formList = new ArrayList<>();
			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int count = ((Number) row[2]).intValue();
				NoOfBlogForm form = new NoOfBlogForm(count, month, year);

				formList.add(form);
				numberToShow++;

			}

			return formList;
		}
	}
	// ...

	public Object getNewEvents(int noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths in the database
			long maxOfMonths = eventsRepository.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = (int) maxOfMonths;
			}
			List<Object[]> result = eventsRepository.countEventsByYearAndMonth();
			List<NoOfEventForm> formList = new ArrayList<>();

			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int count = ((Number) row[2]).intValue();
				NoOfEventForm form = new NoOfEventForm(count, month, year);

				formList.add(form);
				numberToShow++;

			}

			return formList;
		}
	}

	// =======================

	public Object getEnrollTimes(int noOfMonths) {
		long maxMonths = enrollTimesRepo.countDistinctMonths();
		if (noOfMonths <= 0) {
			return null;
		}

		if (maxMonths == 0) {
			return null;
		}
		if (noOfMonths <= 0 || noOfMonths > maxMonths) {
			noOfMonths = (int) maxMonths;
		}

		List<Object[]> result = enrollTimesRepo.totalEnrollByMonths();
		List<NoOfEnrollForm> formList = new ArrayList<>();

		int numberToShow = 1;

		for (Object[] row : result) {

			if (numberToShow > noOfMonths) {
				break;
			}
			int year = ((Number) row[1]).intValue();
			int month = ((Number) row[2]).intValue();
			int count = ((Number) row[0]).intValue();
			NoOfEnrollForm form = new NoOfEnrollForm(count, month, year);

			formList.add(form);

			numberToShow++;

		}
		return formList;
	}

	// =======================
	public Object getDoQuiz(int noOfMonths) {
		long maxMonths = quizResultRepository.countDistinctMonths();
		if(noOfMonths <= 0) {
			return null;
		}
		
		if (maxMonths == 0) {
			return null;
		}
		if (noOfMonths <= 0 || noOfMonths > maxMonths) {
			noOfMonths = (int) maxMonths;
		}

		List<Object[]> result = quizResultRepository.countByYearMonth();
		List<NoOfDoQuizForm> formList = new ArrayList<>();
		int numberToShow = 1;

		for (Object[] row : result) {

			if (numberToShow > noOfMonths) {
				break;
			}

//			int year1 = (int) row[1];
			int year = ((Number) row[1]).intValue();
			int month = ((Number) row[2]).intValue();
			int count = ((Number) row[0]).intValue();
			NoOfDoQuizForm form = new NoOfDoQuizForm(count, month, year);

			formList.add(form);
			numberToShow++;

		}
		return formList;
	}

	// ============================
	public void addEnrollTimes(UserPrinciple user) {
		if (user == null) {
			return;
		}
		// check xem trong ngay co lan dang nhap chua || check xem co user do chua
		// role phai la role member hoac la role editor
		if (user.getRole().equals(RoleName.ADMIN.toString())) {
			return;
		}

		LocalDate currentDate = LocalDate.now();
		List<Object[]> list = enrollTimesRepo.countByDate();
		if (list == null || list.isEmpty()) {
			// today no signin -> save
			EnrollTime enrollTime = new EnrollTime();
			enrollTime.setDate(currentDate);
			enrollTime.setTimes((long) 1);
			enrollTimesRepo.save(enrollTime);
		} else {
			// update + 1
			enrollTimesRepo.incrementTimesByDate();

		}

	}

	public Object GetOveralDashBoard() {
		// no of month = 1
		List<NoOfEnrollForm> listEnrol = (List<NoOfEnrollForm>) getEnrollTimes(1);
		List<NoOfAccMonthForm> listAcc = (List<NoOfAccMonthForm>) getNewAccs(1);
		List<NoOfBlogForm> listBlog = (List<NoOfBlogForm>) getNewBlogs(1);
		List<NoOfEventForm> listEvent = (List<NoOfEventForm>) getNewEvents(1);
		List<NoOfDoQuizForm> listDoQuiz = (List<NoOfDoQuizForm>) getDoQuiz(1);

		Long noOfDoquiz = new Long(0);
		Long noOfEnroll = new Long(0);
		Long noOfBlog = new Long(0);
		Long noOfEvent = new Long(0);
		Integer noOfAcc = new Integer(0);

		if (listAcc != null) {
			noOfAcc = listAcc.get(0).getNoOfUsers();
		}

		if (listEvent != null) {
			noOfEvent = listEvent.get(0).getCount();
		}

		if (listBlog != null) {
			noOfBlog = listBlog.get(0).getCount();
		}
		if (listDoQuiz != null) {
			noOfDoquiz = listDoQuiz.get(0).getCount();
		}
		if (listEnrol != null) {
			noOfEnroll = listEnrol.get(0).getCount();
		}

		return new OverallDashboard(noOfDoquiz, noOfEnroll, noOfBlog, noOfEvent, noOfAcc);

	}

}
