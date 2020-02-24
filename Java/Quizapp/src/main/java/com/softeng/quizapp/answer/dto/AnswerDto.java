package com.softeng.quizapp.answer.dto;

import com.softeng.quizapp.answer.model.Answer;

public class AnswerDto {
    private Long id;
    private String text;
    private boolean correct;
    private Long question_id;
    private String player;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.text = answer.getText();
        this.correct = answer.isCorrect();
        this.question_id = answer.getQuestion().getId();
        this.player = null;
    }

    public AnswerDto(Long id, String text, boolean correct, Long question_id, String player) {
        this.id = id;
        this.text = text;
        this.correct = correct;
        this.question_id = question_id;
        this.player = player;
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
