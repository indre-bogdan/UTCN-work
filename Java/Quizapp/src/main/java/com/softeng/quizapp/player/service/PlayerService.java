package com.softeng.quizapp.player.service;

import com.softeng.quizapp.player.model.Player;
import com.softeng.quizapp.player.repository.PlayerRepository;
import jdk.management.resource.internal.inst.StaticInstrumentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerByName(String name){
        if(playerRepository.findPlayerByName(name).isPresent())
            return playerRepository.findPlayerByName(name).get();
        return null;
    }

    @Async
    public CompletableFuture<Player> getPlayerByNameAsync(String name){
        LOGGER.info("Request to find a player by name");
        return CompletableFuture.completedFuture(playerRepository.findPlayerByName(name).get());
    }

    public void savePlayer(Player player){
        playerRepository.save(player);
    }

    @Async
    public CompletableFuture<Player> updateScore(Player player){
        LOGGER.info("Saving the new score");
        return CompletableFuture.completedFuture(playerRepository.save(player));
    }
}
