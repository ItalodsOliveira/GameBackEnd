package com.isogames.app.controller;

import com.isogames.app.model.BuyGame;
import com.isogames.app.model.Game;
import com.isogames.app.model.request.AumentarEstoque;
import com.isogames.app.model.request.CalculaPreco;
import com.isogames.app.model.request.EfetivarCompra;
import com.isogames.app.model.response.GameResponseCompra;
import com.isogames.app.model.response.GameResponseError;
import com.isogames.app.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("game")
@Tag(name = "Game Controller")
public class GameController {

    @Autowired
    GameService gameService;

    @Operation(summary = "Cadastrar game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Erro", content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGame(@RequestBody Game game) {

        Game gameCriado = gameService.createGame(game);
        if (gameCriado == null) {
            var respostaDeErro = "Não possivel cadastrar game";
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(gameCriado);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGames() {

        List<Game> game = gameService.readGames();
        if (game.isEmpty()) {
            var respostaDeErro = "Nenhum jogo cadastrado";
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameById(@PathVariable(value = "id", required = true) Long id) {

        GameResponseError gameResponseError = new GameResponseError();
        var game = gameService.readGameById(id);
        try {
            if (game.getId() == null) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo com o id {0}", id));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @Operation(summary = "Atualizar game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Erro", content = {@Content(mediaType = "application/json")})
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGame(@RequestBody Game game) {

        Game gameAtualizado = gameService.updateGame(game);
        if (gameAtualizado.getId() == null) {

            var respostaDeErro = "Não Possivel atualizar game vazio";
            ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(gameAtualizado);
    }

    @DeleteMapping(value = "{id}")
    public void deleteGameById(@PathVariable(value = "id", required = true) Long id) {

        gameService.deleteGameById(id);
    }

    @GetMapping(value = "nome-do-jogo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByName(@RequestParam(value = "nomeDoJogo", required = true) String nomeDoJogo) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByName(nomeDoJogo);

        if (game.isEmpty()) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(MessageFormat.format("Não foram encopntrados games com o nome {0}", nomeDoJogo));
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "desenvolvedora", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByDev(@RequestParam(value = "dev", required = true) String desenvolvedora) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByDev(desenvolvedora);

        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foram encopntrados games da desenvolvedora {0}", desenvolvedora));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);

    }

    @GetMapping(value = "distribuidora", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByDistribuidora(@RequestParam(value = "distribuidora", required = true) String distribuidora) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByDistribuidora(distribuidora);


        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foram encopntrados games da distribuidora {0}", distribuidora));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);

    }

    @GetMapping(value = "classe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByClasseIndica(@RequestParam(value = "classificacaoIndiacativa", required = true) String classificacaoIndiacativa) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByClasseIndica(classificacaoIndiacativa);


        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo com o com a classe indicativa {0}", classificacaoIndiacativa));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);

    }

    @GetMapping(value = "preco", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByPrecoIn(@RequestParam(value = "precoin", required = true) float preco) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByPrecoIn(preco);

        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo com o preco {0}", preco));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "precoEntre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByPrecoEntre(@RequestParam(value = "preco1", required = true) float preco1,
                                               @RequestParam(value = "preco2", required = true) float preco2) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.readGameByPrecoEntre(preco1, preco2);

        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo com o preço entre R${0} e R${1}", preco1, preco2));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @PutMapping(value = "atualizaPreco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mudaPrecoByClasseIndica(@RequestBody CalculaPreco calculaPreco) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.mudaPrecoByClasseIndica(calculaPreco.getClassificacaoIndicativa(), calculaPreco.getPercentual(), calculaPreco.isDesconto());

        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo com a classificação indicaiva {0}", calculaPreco.getClassificacaoIndicativa()));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @PutMapping(value = "atualizaPrecoDistribuidora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mudaPrecoByDistribuidora(@RequestBody CalculaPreco calculaPreco) {

        GameResponseError gameResponseError = new GameResponseError();
        List<Game> game = gameService.mudaPrecoByDistribuidora(calculaPreco.getDistribuidora(), calculaPreco.getPercentual(), calculaPreco.isDesconto());

        try {
            if (game.isEmpty()) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo da distribuidora {0}", calculaPreco.getDistribuidora()));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @PostMapping(value = "comprarPorId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity comprarPorId(@RequestBody EfetivarCompra efetivarCompra) {

        GameResponseError gameResponseError = new GameResponseError();
        GameResponseCompra game = gameService.comprarPorId(efetivarCompra.getCodigoDoJogo(), efetivarCompra.getQuantidade());

        try {
            if (game == null) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo da com o codigo {0}", efetivarCompra.getCodigoDoJogo()));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }
    @PostMapping(value = "aumentarEstoquePorId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity aumentarEstoquePorId(@RequestBody AumentarEstoque aumentarEstoque){

        GameResponseError gameResponseError = new GameResponseError();
        Game game = gameService.aumentarEstoquePorId(aumentarEstoque.getCodigoDoJogo(), aumentarEstoque.getQuantidadeSomaEstoque());

        try {
            if (game.getId() == null) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhum jogo da com o codigo {0}", aumentarEstoque.getCodigoDoJogo()));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "devolverPorId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity devolverPorId(@RequestParam(value = "idDaCompra", required = true) int idDaCompra,
                                        @RequestParam(value = "quantidadeDevolvida", required = true) int quantidadeDevolvida){

        GameResponseError gameResponseError = new GameResponseError();
        BuyGame gameDevolvido = gameService.devolvoverPorId(idDaCompra, quantidadeDevolvida);

        try {
            if (gameDevolvido.getId() == null) {
                gameResponseError.setHttpCode(400);
                gameResponseError.setMensagemDeErro(MessageFormat.format("Não foi encontrado nenhuma compra do game {0}", gameDevolvido.getId()));
                gameResponseError.setHoraDoErro(new Date());

                return ResponseEntity.badRequest().body(gameResponseError);
            }
        } catch (Exception e) {

            gameResponseError.setHttpCode(400);
            gameResponseError.setMensagemDeErro(e.getMessage());
            gameResponseError.setHoraDoErro(new Date());

            return ResponseEntity.badRequest().body(gameResponseError);
        }
        return ResponseEntity.ok().body(gameDevolvido);
    }
}