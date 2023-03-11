package com.example.teamfind;

import java.time.ZonedDateTime;

public class User {
    public String name;
    public String id;
    //public Category[] categories;
    //private ZonedDateTime zt;
    public Project[] projects;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        //this.categories = categories;
    }



    void newProject(String name, Category[] categories){

    }
}
