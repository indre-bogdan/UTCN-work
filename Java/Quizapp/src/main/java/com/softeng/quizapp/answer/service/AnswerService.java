package com.softeng.quizapp.answer.service;

import com.softeng.quizapp.answer.dto.AnswerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AnswerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);

    @Async
    public CompletableFuture<Boolean> checkIfAnswerIsRight(AnswerDto answerDto){
        LOGGER.info("Answer is " + (answerDto.isCorrect() ? "correct" : "false"));
        return CompletableFuture.completedFuture(answerDto.isCorrect());
    }
}
