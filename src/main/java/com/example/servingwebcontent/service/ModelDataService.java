package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Person;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ModelDataService implements ModelService {

    public static final Path HISTORY = Path.of("history");
    Map<Long, Person> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public void addUserToBase(Person person) {
        person.setId(idCounter.incrementAndGet());
        mapGreetingHistory.put(idCounter.get(), person);
    }

    @Override
    public List<Person> getHistorySortByName() {
        return mapGreetingHistory.values()
                .stream()
                .sorted(Comparator.comparing(person -> person.getName().toLowerCase()))
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
        return mapGreetingHistory.values().stream().map(Person::convertToJson).collect(Collectors.toList());
    }

    @Override
    public List<Person> getHistory() {
        return mapGreetingHistory.values().stream().collect(Collectors.toList());
    }

}
