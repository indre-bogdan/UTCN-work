package com.softeng.quizapp.answer.controller;

import com.softeng.quizapp.answer.dto.AnswerDto;
import com.softeng.quizapp.answer.service.AnswerService;
import com.softeng.quizapp.player.model.Player;
import com.softeng.quizapp.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AnswerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private AnswerService answerService;
    @PostMapping("/answers")
    public ResponseEntity submitAnswer(@RequestBody AnswerDto answerDto){
        try {
            CompletableFuture<Player> fetchPlayer=playerService.getPlayerByNameAsync(answerDto.getPlayer());
            CompletableFuture<Boolean> correct = answerService.checkIfAnswerIsRight(answerDto);
            Player player = fetchPlayer.get();

            CompletableFuture.allOf(fetchPlayer, correct).join();
            if (correct.get())
                player.setScore(player.getScore() + 1);
            playerService.updateScore(player);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
