package com.example.servingwebcontent.model;

import javax.json.Json;

public class Person {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAdress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String toTextFormat() {
        System.out.println(id);
        return String.format("%d, %s,", id, name);
    }

    public String convertToJson() {
        String personObject = Json.createObjectBuilder()
                .add("Id", id)
                .add("Name", name)
                .add("Surname", surname)
                .add("PhoneNumber", phoneNumber)
                .add("Email", emailAdress)
                .build().toString();
        return personObject;
    }
}
