package com.isogames.app.service;

import com.isogames.app.model.Game;
import com.isogames.app.model.response.GameResponseCompra;
import com.isogames.app.model.response.GameResponseError;
import com.isogames.app.repository.GameRepository;
import com.isogames.app.utils.AjustaPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GameService {

    private final Logger logger = Logger.getLogger(GameService.class.getName());

    @Autowired
    GameRepository gameRepository;

    @Autowired
    AjustaPreco ajustaPreco;

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

        GameResponseError gameResponseError = new GameResponseError();
        Game game = new Game();
        try {
            game = gameRepository.findById(String.valueOf(id)).orElseThrow();
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return game;
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
            gameAtualizado.setQuantidadeEmEstoque(game.getQuantidadeEmEstoque());

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

    public List<Game> readGameByPrecoIn(float preco) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorPreco(preco);
            logger.info(MessageFormat.format("Foram encontrados {0} games com Sucesso com o preço {1}", game.size(), preco));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public List<Game> readGameByPrecoEntre(float preco1, float preco2) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorPrecoEntre(preco1, preco2);
            logger.info(MessageFormat.format("Foram encontrados {0} games com o preço entre {1} e {2}", game.size(), preco1, preco2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public List<Game> mudaPrecoByClasseIndica(String classificacaoIndiacativa, float percentual, boolean desconto) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorClasseIndica(classificacaoIndiacativa);
            logger.info(MessageFormat.format("{0} foram encontrados", game.size()));

            for (int i = 0; i < game.size(); i++) {
                if (desconto == true) {
                    game.get(i).setPreco(ajustaPreco.aplicarDesconto(game.get(i).getPreco(), percentual));
                    gameRepository.save(game.get(i));
                    logger.info(MessageFormat.format("Foi aplicado um desconto de {0}%, para a classificação indicativa {1}", percentual, classificacaoIndiacativa));
                } else {
                    game.get(i).setPreco(ajustaPreco.aplicarAumento(game.get(i).getPreco(), percentual));
                    gameRepository.save(game.get(i));
                    logger.info(MessageFormat.format("Foi aplicado um aumento de {0}%, para a classificação indicativa {1}", percentual, classificacaoIndiacativa));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public List<Game> mudaPrecoByDistribuidora(String distribuidora, float percentual, boolean desconto) {

        List<Game> game = new ArrayList<>();
        try {
            game = gameRepository.pesquisaPorDistribuidora(distribuidora);
            logger.info(MessageFormat.format("{0} foram encontrados", game.size()));

            for (int i = 0; i < game.size(); i++) {
                if (desconto == true) {
                    game.get(i).setPreco(ajustaPreco.aplicarDesconto(game.get(i).getPreco(), percentual));
                    gameRepository.save(game.get(i));
                    logger.info(MessageFormat.format("Foi aplicado um desconto de {0}%, para a distribuidora {1}", percentual, distribuidora));
                } else {
                    game.get(i).setPreco(ajustaPreco.aplicarAumento(game.get(i).getPreco(), percentual));
                    gameRepository.save(game.get(i));
                    logger.info(MessageFormat.format("Foi aplicado um aumento de {0}%, para a distribuidora {1}", percentual, distribuidora));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    public GameResponseCompra comprarPorId(int codigoDoJogo, int quantidade) {

        GameResponseCompra respostaDeSucesso = new GameResponseCompra();
        try {
            Game game = gameRepository.findById(String.valueOf(codigoDoJogo)).orElseThrow();
            logger.info(MessageFormat.format("O game {0} foi encontrado", game.getNomeDoJogo()));
            if (quantidade <= game.getQuantidadeEmEstoque()) {

                respostaDeSucesso.setPrecoTotal(game.getPreco() * quantidade);
                respostaDeSucesso.setQuantidade(quantidade);
                respostaDeSucesso.setNomeDoJogo(game.getNomeDoJogo());
                respostaDeSucesso.setPrecoUnidade(game.getPreco());
                respostaDeSucesso.setHoraDaCompra(new Date());
                respostaDeSucesso.setMensagem("Compra realizada com sucesso");

                game.setQuantidadeEmEstoque(game.getQuantidadeEmEstoque() - quantidade);
                logger.info(MessageFormat.format("A compra do jogo {0} fica R${1} = preço por unidade R${2} vezes quantidade {3}, ficam no estoque {4}", game.getNomeDoJogo(), respostaDeSucesso.getPrecoTotal(), respostaDeSucesso.getPrecoUnidade(), quantidade, game.getQuantidadeEmEstoque()));
                gameRepository.save(game);
            } else {

                respostaDeSucesso.setPrecoTotal(game.getPreco() * quantidade);
                respostaDeSucesso.setQuantidade(quantidade);
                respostaDeSucesso.setNomeDoJogo(game.getNomeDoJogo());
                respostaDeSucesso.setPrecoUnidade(game.getPreco());
                respostaDeSucesso.setHoraDaCompra(new Date());
                respostaDeSucesso.setMensagem(MessageFormat.format("Compra não realizada, produto em estoque = {0}", game.getQuantidadeEmEstoque()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return respostaDeSucesso;
    }

}
