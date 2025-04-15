package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MessageListener {

    private final List<String> messages = new LinkedList<>();

    @RabbitListener(queues = "demo-queue")
    public void receive(String message) {
        messages.add(0, message); // нові повідомлення в початок
        if (messages.size() > 100) {
            messages.remove(messages.size() - 1);
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}