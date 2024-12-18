package com.isogames.app.controller;

import com.isogames.app.model.Game;
import com.isogames.app.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game createGame(@RequestBody Game game) {

        return gameService.createGame(game);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> readGames() {

        return gameService.readGames();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Game readGameById(@PathVariable(value = "id", required = true)Long id){

        return gameService.readGameById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Game updateGame(@RequestBody Game game) {

        return gameService.updateGame(game);
    }

    @DeleteMapping(value = "{id}")
    public void deleteGameById(@PathVariable(value = "id",required = true) Long id){

        gameService.deleteGameById(id);
    }

    @GetMapping(value = "nome-do-jogo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> readGameByName(@RequestParam(value = "nomeDoJogo", required = true)String nomeDoJogo){

        return gameService.readGameByName(nomeDoJogo);
    }
}