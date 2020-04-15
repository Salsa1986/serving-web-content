package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Person;
import com.example.servingwebcontent.service.ModelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping
public class GreetingController {

    ModelDataService modelDataService;
    Map<Long, String> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Autowired
    public GreetingController(ModelDataService modelDataService) {
        this.modelDataService = modelDataService;
    }

    @GetMapping("/person")
    public String greeting(Model model) {
        model.addAttribute("greeting", new Person());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String getLogin(@ModelAttribute Person person, Model model){
        modelDataService.addUserToBase(person);
        modelDataService.saveHistoryToFile();
        model.addAttribute("history", modelDataService.getHistory());
        return "greeting";
    }

}
