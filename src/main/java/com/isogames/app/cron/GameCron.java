package com.isogames.app.cron;

import com.isogames.app.model.Game;
import com.isogames.app.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class GameCron {


    private final Logger logger = Logger.getLogger(GameCron.class.getName());

    @Autowired
    GameService gameService;

    @Scheduled(cron = "${spring.scheduler.start}", zone = "${spring.scheduler.zone}")
    public void testeDeCron() {
        logger.info("Scheduler disparado com sucesso");
    }

    @Scheduled(cron = "${spring.scheduler.start}", zone = "${spring.scheduler.zone}")
    public void verificaEstoque() {

        try {
            List<Game> game = gameService.readGames();
            for (int i = 0; i < game.size(); i++) {
                if (game.get(i).getQuantidadeEmEstoque() <= 5) {
                    gameService.aumentarEstoquePorId(game.get(i).getId().intValue(), 20);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
