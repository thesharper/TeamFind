package com.example.teamfind;

import java.time.ZonedDateTime;

public class User {
    public String first_name;
    public String second_name;
    public String name;
    public String id;
    public Project[] projects;
    public String email;

    public User(String fname, String sname, String id, String email) {
        this.first_name = fname;
        this.second_name = sname;
        this.id = id;
        this.email = email;
        this.name = first_name + " " + second_name;
    }

    static User getById(String id){
        //TODO
        return new User("", "", "", "");
    }

    void newProject(String name, Category[] categories){

    }
}
