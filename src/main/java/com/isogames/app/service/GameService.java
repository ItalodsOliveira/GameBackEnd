package com.isogames.app.service;

import com.isogames.app.model.Game;
import com.isogames.app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GameService {

    private final Logger logger = Logger.getLogger(GameService.class.getName());

    @Autowired
    GameRepository gameRepository;

    public Game createGame(Game game) {

        Game gameCriado = new Game();

        try {
            gameCriado = gameRepository.save(game);
            logger.info("Game cadastrado com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gameCriado;
    }

    public List<Game> readGames() {

        List<Game> allGames = new ArrayList<>();
        try {
            allGames = gameRepository.findAll();
            logger.info("Games encontrados");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allGames;
    }

    public Game readGameById(Long id) {

        Game game = new Game();
        try {
            game = gameRepository.findById(String.valueOf(id)).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info(MessageFormat.format("O jogo {0} foi encontrado com sucesso com o preço de R${1}", game.getNomeDoJogo(), game.getPreco()));
        return game;
    }

    public Game updateGame(Game game) {

        Game gameAtualizado = new Game();
        try {
            gameAtualizado = gameRepository.findById(String.valueOf(game.getId())).orElseThrow();
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

            gameRepository.save(gameAtualizado);
            logger.info(MessageFormat.format("O game {0} foi atualizado com sucesso", gameAtualizado.getNomeDoJogo()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return gameAtualizado;
    }

    public void deleteGameById(Long id) {

        try {
            Game game = gameRepository.findById(String.valueOf(id)).orElseThrow();
            gameRepository.delete(game);
            logger.info("Game deletado com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Game> readGameByName(String nomeDoJogo) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorNome(nomeDoJogo);
            logger.info(MessageFormat.format("{0} foram encontrados com Sucesso", game.size()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public List<Game> readGameByDev(String desenvolvedora) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorDev(desenvolvedora);
            logger.info(MessageFormat.format("{0} foram encontrados com Sucesso", game.size()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }


    public List<Game> readGameByDistribuidora(String distribuidora) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorDistribuidora(distribuidora);
            logger.info(MessageFormat.format("{0} foram encontrados com Sucesso", game.size()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public List<Game> readGameByClasseIndica(String classificacaoIndiacativa) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorClasseIndica(classificacaoIndiacativa);
            logger.info(MessageFormat.format("{0} foram encontrados com Sucesso", game.size()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }
}
