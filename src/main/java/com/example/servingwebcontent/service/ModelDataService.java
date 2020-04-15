package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Person;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ModelDataService implements ModelService {

    public static final Path HISTORY = Path.of("history");
    Map<Long, Person> mapGreetingHistory = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public void addUserToBase(Person person) {
        mapGreetingHistory.put(idCounter.getAndIncrement(), person);
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
        return mapGreetingHistory.values().stream().map(Person::toTextFormat).collect(Collectors.toList());
    }

    @Override
    public List<Person> getHistory() {
        return mapGreetingHistory.values().stream().collect(Collectors.toList());
    }

    @Override
    public JsonObject convertToJson(Person person) {
        JsonObject personObject = Json.createObjectBuilder()
                .add("Id", person.getId())
                .add("Name", person.getName())
                .add("Surname", person.getSurname())
                .add("Login", person.getLogin())
                .add("PhoneNumber", person.getPhoneNumber())
                .add("Email", person.getEmailAdress())
                .build();
        return personObject;
    }

}
