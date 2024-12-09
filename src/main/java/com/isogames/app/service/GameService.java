package com.isogames.app.service;

import com.isogames.app.model.Game;
import com.isogames.app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GameService {

    private final Logger logger = Logger.getLogger(GameService.class.getName());

    @Autowired
    GameRepository gameRepository;

    public Game createGame(Game game) {

        logger.info("Game cadastrado com sucesso");
        return gameRepository.save(game);
    }

    public List<Game> readGames() {

        logger.info("Games encontrados");
        return gameRepository.findAll();
    }

    public Game readGameById(Long id) {

        var game = gameRepository.findById(id).orElseThrow();
        logger.info(MessageFormat.format("O jogo {0} foi encontrado com sucesso com o preço de R${1}", game.getNomeDoJogo(), game.getPreco()));
        return game;
    }

    public Game updateGame(Game game) {

        var gameAtualizado = gameRepository.findById(game.getId()).orElseThrow();
        gameAtualizado.setNomeDoJogo(game.getNomeDoJogo());
        gameAtualizado.setDataDeLancamaento(game.getDataDeLancamaento());
        gameAtualizado.setDescricao(game.getDescricao());
        gameAtualizado.setDesenvolvedora(game.getDesenvolvedora());
        gameAtualizado.setDistribuidora(game.getDistribuidora());
        gameAtualizado.setPreco(game.getPreco());
        gameAtualizado.setPlataforma(game.getPlataforma());
        gameAtualizado.setGenero(game.getGenero());
        gameAtualizado.setClassificacaoIndiacativa(game.getClassificacaoIndiacativa());
        gameAtualizado.setIdiomas(game.getIdiomas());
        gameAtualizado.setLegendas(game.getLegendas());

        logger.info(MessageFormat.format("O game {0} foi atualizado com sucesso", gameAtualizado.getNomeDoJogo()));
        return gameRepository.save(gameAtualizado);
    }

    public void deleteGameById(Long id){

        var game = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(game);
        logger.info("Game deletado com sucesso");
    }
}
