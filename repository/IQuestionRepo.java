package com.swp.vnhistory.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swp.vnhistory.dto.respone.QuestionForm;
import com.swp.vnhistory.model.Question;

public interface IQuestionRepo extends JpaRepository<Question, Long> {
	List<Question> findAllByQuiz_QuizID(long quizId);

	// find question containing

	@Query("SELECT new com.swp.vnhistory.dto.respone.QuestionForm(q.questionID, q.question, q.option1, q.option2, q.option3, q.option4, q.correctAnswer, q.quiz.quizID) "
	        + "FROM Question q "
	        + "WHERE q.quiz.quizID = :quizId "
	        + "AND (:keyword = '' OR LOWER(q.question) LIKE CONCAT('%', LOWER(:keyword), '%'))")
	List<QuestionForm> findAllQuestionFormsByQuizIdAndKeyword(@Param("quizId") long quizId, @Param("keyword") String keyword);

	

	// update question
	@Transactional
	@Modifying
	@Query("UPDATE Question q SET q.option1 = :option1, q.option2 = :option2, q.option3 = :option3, "
			+ "q.option4 = :option4, q.correctAnswer = :correctAnswer, q.question = :question "
			+ "WHERE q.questionID = :questionId")
	void updateQuestionById(long questionId, String option1, String option2, String option3, String option4,
			String correctAnswer, String question);

}
