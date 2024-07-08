package com.tech.magalums.cheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import com.tech.magalums.service.NotificationService;

@Component
public class MagaluTaskScheduler {

    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class);

    private final NotificationService notificationService;

    public MagaluTaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 60000) // 1 minuto em milissegundos
    public void checkTasks() {
        var dateTime = LocalDateTime.now();
        logger.info("Executando em {}", dateTime);
        try {
            notificationService.checkAndSend(dateTime);
        } catch (Exception e) {
            logger.error("Erro ao executar checkTasks(): {}", e.getMessage());
            // Trate o erro conforme necessário
        }
    }
}
