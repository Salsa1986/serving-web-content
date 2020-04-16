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
public class PersonController {

    ModelDataService modelDataService;
    Map<Long, String> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Autowired
    public PersonController(ModelDataService modelDataService) {
        this.modelDataService = modelDataService;
    }

    @GetMapping("/person")
    public String greeting(Model model) {
        model.addAttribute("person", new Person());
        return "person";
    }

    @PostMapping("/person")
    public String getLogin(@ModelAttribute Person person, Model model){
        modelDataService.addUserToBase(person);
        modelDataService.saveHistoryToFile();
        model.addAttribute("history", modelDataService.getHistory());
        return "person";
    }

}
