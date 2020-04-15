package com.example.servingwebcontent.service;

import com.example.servingwebcontent.Greeting;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class GreetingDataService implements GreetingService {

    public static final Path HISTORY = Path.of("history");
    Map<Long, Greeting> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public void addUserToHistory(String name) {
        Greeting greeting = new Greeting();
        greeting.setId(idCounter.get());
        greeting.setName(name);
        mapGreetingHistory.put(idCounter.getAndIncrement(), greeting);
    }

    @Override
    public List<Greeting> getHistorySortByName() {
        return mapGreetingHistory.values()
                .stream()
                .sorted(Comparator.comparing(greeting -> greeting.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveHistoryToFile() {
        if (Files.notExists(HISTORY)) {
            try {
                Files.createFile(HISTORY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(HISTORY, getHistoryToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> getHistoryToSave() {
        return mapGreetingHistory.values().stream().map(Greeting::toTextFormat).collect(Collectors.toList());
    }

    @Override
    public List<Greeting> getHistory() {
        return mapGreetingHistory.values().stream().collect(Collectors.toList());
    }

}
