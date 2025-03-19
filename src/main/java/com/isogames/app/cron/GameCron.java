package com.isogames.app.cron;

import com.isogames.app.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
}
