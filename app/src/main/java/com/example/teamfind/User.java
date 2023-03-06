package com.example.teamfind;

import java.time.ZonedDateTime;

public class User {
    public String name;
    public Category[] categories;
    private ZonedDateTime zt;

    public User(String name) {
        this.name = name;
        //this.categories = categories;
    }

    void newProject(String name, Category[] categories){

    }
}
