package com.example.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/messages")
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String send(@RequestParam(required = false) String text) {
        String message = text != null ? text : "Hello from producer at " + LocalDateTime.now();
        rabbitTemplate.convertAndSend("demo-exchange", "demo-routing-key", message);
        return "Sent: " + message;
    }
}
