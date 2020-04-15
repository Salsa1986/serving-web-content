package com.example.servingwebcontent;

import com.example.servingwebcontent.service.GreetingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
public class GreetingController {

    GreetingDataService greetingDataService;
    Map<Long, String> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Autowired
    public GreetingController(GreetingDataService greetingDataService) {
        this.greetingDataService = greetingDataService;
    }

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String getLogin(@ModelAttribute Greeting greeting, Model model){
        greetingDataService.addUserToHistory(greeting.getName());
        greetingDataService.saveHistoryToFile();
        model.addAttribute("history", greetingDataService.getHistory());
        return "greeting";
    }

}
