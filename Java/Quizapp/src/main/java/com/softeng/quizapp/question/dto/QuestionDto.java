package com.softeng.quizapp.question.dto;

import com.softeng.quizapp.question.model.Question;

public class QuestionDto {
    private Long id;
    private String text;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.text = question.getText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
