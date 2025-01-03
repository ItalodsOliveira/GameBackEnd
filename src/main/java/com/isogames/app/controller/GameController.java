package com.isogames.app.controller;

import com.isogames.app.model.Game;
import com.isogames.app.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Collections;
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

        Game game = gameService.readGameById(id);
        if (game == null) {
            var respostaDeErro = MessageFormat.format("Não foi encontrado nenhum jogo com o id {0}", id);
            ResponseEntity.badRequest().body(respostaDeErro);
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
        if (gameAtualizado == null) {

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

        List<Game> game = gameService.readGameByName(nomeDoJogo);

        if (game.isEmpty()) {
            var respostaDeErro = MessageFormat.format("Não foram encopntrados games com o nome {0}", nomeDoJogo);
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "desenvolvedora", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByDev(@RequestParam(value = "dev", required = true) String desenvolvedora) {

        List<Game> game = gameService.readGameByDev(desenvolvedora);

        if (game.isEmpty()) {
            var respostaDeErro = MessageFormat.format("Não foram encopntrados games da desenvolvedora {0}", desenvolvedora);
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(game);

    }

    @GetMapping(value = "distribuidora", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByDistribuidora(@RequestParam(value = "distribuidora", required = true) String distribuidora) {

        List<Game> game = gameService.readGameByDistribuidora(distribuidora);

        if (game.isEmpty()) {
            var respostaDeErro = MessageFormat.format("Não foram encopntrados games da distribuidora {0}", distribuidora);
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(game);

    }

    @GetMapping(value = "classe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity readGameByClasseIndica(@RequestParam(value = "classificacaoIndiacativa", required = true) String classificacaoIndiacativa) {

        List<Game> game = gameService.readGameByClasseIndica(classificacaoIndiacativa);

        if (game.isEmpty()) {
            var respostaDeErro = MessageFormat.format("Não foram encopntrados games, {0}", classificacaoIndiacativa);
            return ResponseEntity.badRequest().body(respostaDeErro);
        }
        return ResponseEntity.ok().body(game);

    }


}