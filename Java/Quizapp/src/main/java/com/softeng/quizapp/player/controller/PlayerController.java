package com.softeng.quizapp.player.controller;

import com.softeng.quizapp.player.model.Player;
import com.softeng.quizapp.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("player")
    public ResponseEntity postPlayer(@RequestParam String name){
        if(name != null && playerService.getPlayerByName(name) == null) {
            Player player = new Player(name, (long) 0);
            playerService.savePlayer(player);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("player/{name}/score")
    public ResponseEntity<Long> getScoreForPlayer(@PathVariable String name){
        return new ResponseEntity<Long>(playerService.getPlayerByName(name).getScore(), HttpStatus.OK);
    }

}
