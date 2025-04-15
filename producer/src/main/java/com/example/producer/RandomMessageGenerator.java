package com.example.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class RandomMessageGenerator {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public RandomMessageGenerator(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendRandomMessage() {
        String message = "AutoMsg-" + random.nextInt(1000) + " @ " + LocalDateTime.now();
        rabbitTemplate.convertAndSend("demo-exchange", "demo-routing-key", message);
        System.out.println("Sent: " + message);
    }
}
