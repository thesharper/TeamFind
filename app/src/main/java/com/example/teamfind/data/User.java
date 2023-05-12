package com.example.teamfind.data;

public class User {
    public String first_name;
    public String second_name;
    public String name;
    public String id;
    public String email;
    public static User thisUser;

    public User(String first_name, String second_name, String id, String email) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.id = id;
        this.email = email;
        this.name = first_name + " " + second_name;
    }
    public User(){}
}