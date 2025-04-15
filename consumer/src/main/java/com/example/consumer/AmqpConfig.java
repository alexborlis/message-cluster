package com.example.consumer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Queue demoQueue() {
        return new Queue("demo-queue", true); // durable = true
    }
}
