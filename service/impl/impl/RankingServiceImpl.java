package com.swp.vnhistory.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.vnhistory.dto.respone.GeneralRankingForm;
import com.swp.vnhistory.dto.respone.PrivateRankingForm;
import com.swp.vnhistory.model.Ranking;
import com.swp.vnhistory.model.User;
import com.swp.vnhistory.repository.IAccountRepository;
import com.swp.vnhistory.repository.IQuizResultRepository;
import com.swp.vnhistory.repository.IRankingRepository;

@Service
public class RankingServiceImpl {

	@Autowired
	IRankingRepository rankingRepository;
	@Autowired
	IAccountRepository accountRepository;
	@Autowired
	IQuizResultRepository quizResultRepository;
	@Autowired
	AccountServiceImpl accountServiceImpl;
//	private String userName;
//	private int scores;
//	private int numbOfQuizzes;

	public List<GeneralRankingForm> getTheRankingTable(String keyword) {
//		public List<User> getTheRankingTable(String keyword){
		if (keyword == null) {
			keyword = "";
		}

		// input keyword || not ->
		List<User> listUser = accountServiceImpl.SearchByUserName(keyword);
		List<GeneralRankingForm> ranking = new ArrayList<>();
		int totalScore;
		int totalQuiz;

		for (User user : listUser) {
			String userName = user.getUsername();
			Ranking rankObjTmp = rankingRepository.findByUserUserId(user.getUserId());
			if (rankObjTmp == null) {
				totalQuiz = 0;
				totalScore = 0;
			}

			else {
				totalScore = rankObjTmp.getTotal_score();

				totalQuiz = quizResultRepository.getTotalQuiz(user.getUserId());
			}
			GeneralRankingForm form = new GeneralRankingForm(userName, totalScore, totalQuiz);
			ranking.add(form);
		}

		return ranking;
	}

	// private top
	public PrivateRankingForm getPrivateRanking(Long userId) {
		if (userId == null) {
			return null;
		}
		if (rankingRepository.findByUserUserId(userId) == null) {
			return null;
		} else {
			// return top
			// query -> new PrivateRankingForm
			List<Ranking> listRank = rankingRepository.findAllOrderByTotalScoreDesc();
			int count = 1;
			for (Ranking i : listRank) {
				if (i.getUser().getUserId() == userId) {
					break;
				}
				count++;
			}

			return new PrivateRankingForm(count);

		}
	}

}
