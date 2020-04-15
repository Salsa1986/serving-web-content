package com.example.servingwebcontent.service;

import com.example.servingwebcontent.Greeting;

import java.io.IOException;
import java.util.List;

public interface GreetingService {

    void addUserToHistory(String name);
    List<Greeting> getHistorySortByName();
    void saveHistoryToFile() throws IOException;
    List<String> getHistoryToSave();
    List<Greeting> getHistory();
}
