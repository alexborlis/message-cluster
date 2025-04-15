package com.example.consumer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WebController {

    private final MessageListener messageListener;

    public WebController(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("messages", messageListener.getMessages());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<String> allMessages() {
        return messageListener.getMessages();
    }
}