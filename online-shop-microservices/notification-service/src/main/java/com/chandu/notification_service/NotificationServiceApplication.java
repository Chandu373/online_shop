package com.chandu.notification_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j

public class NotificationServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderEvent orderEvent) {
        log.info("Received Notification from order" + orderEvent.toString());
    }
}
