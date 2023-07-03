package com.swp.vnhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swp.vnhistory.model.Question;
import com.swp.vnhistory.model.QuestionResult;
import com.swp.vnhistory.model.Quiz;
import com.swp.vnhistory.model.User;

public interface IQuestionResultRepo extends JpaRepository<QuestionResult, Long>{

	   QuestionResult findByUserAndQuizAndQuestion(User user, Quiz quiz, Question question);

}
