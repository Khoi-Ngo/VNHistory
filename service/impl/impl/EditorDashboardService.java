package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.request.EditorDashboardRequest;
import com.swp.vnhistory.dto.respone.NoOfEventForm;
import com.swp.vnhistory.dto.respone.NoOfEvent_Dynasty;
import com.swp.vnhistory.dto.respone.NoOfEvent_Location;
import com.swp.vnhistory.dto.respone.NoOfQuizCreatedForm;
import com.swp.vnhistory.repository.IDynastyRepo;
import com.swp.vnhistory.repository.IEventsRepository;
import com.swp.vnhistory.repository.ILocationRepo;
import com.swp.vnhistory.repository.IQuizRepo;
import com.swp.vnhistory.repository.IUserRepository;

@Service
public class EditorDashboardService {

	@Autowired
	IEventsRepository eventsRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IQuizRepo quizRepo;

	@Autowired
	ILocationRepo locationRepo;
	@Autowired
	IDynastyRepo dynastyRepo;

	public Object getNewEvents(long noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths in the database
			long maxOfMonths = eventsRepository.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = maxOfMonths;
			}
			List<Object[]> result = eventsRepository.countEventsByYearAndMonthByEditor();
			List<NoOfEventForm> formList = new ArrayList<>();

			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int count = ((Number) row[2]).intValue();
				NoOfEventForm calForm = new NoOfEventForm(count, month, year);

				formList.add(calForm);
				numberToShow++;

			}

			return formList;
		}
	}

	public Object getQuizzesCreated(long noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths in the database
			long maxOfMonths = quizRepo.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = maxOfMonths;
			}
			List<Object[]> result = quizRepo.countQuizzesByYearAndMonthByEditor();
			List<NoOfQuizCreatedForm> formList = new ArrayList<>();

			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int count = ((Number) row[2]).intValue();
				NoOfQuizCreatedForm calForm = new NoOfQuizCreatedForm(count, month, year);

				formList.add(calForm);
				numberToShow++;

			}

			return formList;
		}
	}

	public Object getEventsLocation(long noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		} else {
			// check noOfMonths in the database
			long maxOfMonths = eventsRepository.countDistinctMonths();
			if (maxOfMonths == 0) {
				return null;
			}
			if (noOfMonths > maxOfMonths) {
				noOfMonths = maxOfMonths;
			}

			// change this

			List<Object[]> result = eventsRepository.countEventsByYearAndMonthByEditorByLocation();
			List<NoOfEvent_Location> formList = new ArrayList<>();

			int numberToShow = 1;

			for (Object[] row : result) {

				if (numberToShow > noOfMonths) {
					break;
				}
				int year = ((Number) row[0]).intValue();
				int month = ((Number) row[1]).intValue();
				int locationId = ((Number) row[2]).intValue();
				String locationName = "";
				try {
					locationName = locationRepo.getById(new Long(locationId)).getLocationName();

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				int count = ((Number) row[3]).intValue();
				NoOfEvent_Location calForm = new NoOfEvent_Location(count, month, year, locationId, locationName);

				formList.add(calForm);
				numberToShow++;

			}

			return formList;
		}
	}

	public Object getEventsDynasty(long noOfMonths) {
		if (noOfMonths <= 0) {
			return null;
		}

		// check noOfMonths in the database
		long maxOfMonths = eventsRepository.countDistinctMonths();
		if (maxOfMonths == 0) {
			return null;
		}
		if (noOfMonths > maxOfMonths) {
			noOfMonths = maxOfMonths;
		}

		// change this

		List<Object[]> result = eventsRepository.countEventsByYearAndMonthByEditorByDynasty();
		List<NoOfEvent_Dynasty> formList = new ArrayList<>();

		int numberToShow = 1;

		for (Object[] row : result) {

			if (numberToShow > noOfMonths) {
				break;
			}
			int year = ((Number) row[0]).intValue();
			int month = ((Number) row[1]).intValue();
			int dynastyId = ((Number) row[2]).intValue();
			String dynastyName = "";
			try {
				dynastyName = dynastyRepo.getById(new Long(dynastyId)).getDynastyName();

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			int count = ((Number) row[3]).intValue();
			NoOfEvent_Dynasty calForm = new NoOfEvent_Dynasty(dynastyName, count, year, month, dynastyId);

			formList.add(calForm);
			numberToShow++;

		}

		return formList;

	}
}
