package com.softeng.quizapp.question.controller;

import com.softeng.quizapp.answer.dto.AnswerDto;
import com.softeng.quizapp.answer.model.Answer;
import com.softeng.quizapp.question.dto.QuestionDto;
import com.softeng.quizapp.question.model.Question;
import com.softeng.quizapp.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public QuestionDto findQuestionById(@PathVariable Long id){
        return new QuestionDto(questionService.findById(id));
    }

    @GetMapping("question/{id}/answers")
    public List<AnswerDto> getAnswersForQuestion(@PathVariable Long id){
        return StreamSupport.stream(questionService.findAnswersForQuestion(id).spliterator(), false).map(AnswerDto::new).collect(Collectors.toList());

    }

}
