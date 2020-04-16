package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Person;

import java.io.IOException;
import java.util.List;

public interface ModelService {

    void addUserToBase(Person person);
    List<Person> getHistorySortByName();
    void saveHistoryToFile() throws IOException;
    List<String> getHistoryToSave();
    List<Person> getHistory();
}
