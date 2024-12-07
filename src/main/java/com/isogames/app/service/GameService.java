package com.isogames.app.service;

import com.isogames.app.model.Game;
import com.isogames.app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class GameService {

    private final Logger logger = Logger.getLogger(GameService.class.getName());

    @Autowired
    GameRepository gameRepository;

    public Game createGame(Game game){

        logger.info("Game cadastrado com sucesso");
        return gameRepository.save(game);
    }
    public List<Game> readGames(){

        logger.info("Games encontrados");
        return gameRepository.findAll();
    }
}
