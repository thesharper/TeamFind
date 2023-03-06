package com.example.teamfind;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Project {
    public String name;
    public String description;
    public String date;
    public User author;
    public Category[] categories;
    public ProjectFragment fragment;
    static Category nullCategory = new Category("", 0);

    public Project(String name, String description, Category[] categories, User author) {
        this.name = name;
        //this.description = description;
        this.categories = new Category[5];
        for (int i = 0; i < 5; i++) {
            if(i >= categories.length)
                this.categories[i] = nullCategory;
            else
                this.categories[i] = categories[i];
        }
        this.date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        this.author = author;
        fragment = new ProjectFragment(this);
    }
}
