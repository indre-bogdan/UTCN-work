package com.softeng.quizapp.question.service;

import com.softeng.quizapp.answer.model.Answer;
import com.softeng.quizapp.question.model.Question;
import com.softeng.quizapp.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question findById(Long id){
        return questionRepository.findById(id).get();
    }

    public List<Answer> findAnswersForQuestion(Long qId){
        Question question = findById(qId);
        if(question.getAnswers().isEmpty())
            return Collections.emptyList();
        else
            return question.getAnswers();
    }
}
