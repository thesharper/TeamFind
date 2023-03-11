package com.example.teamfind;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Project {
    public String name;
    public String description;
    public String date;
    public User author;
    public Category[] categories;
    //public ProjectFragment fragment;
    private String PROJECT_KEY = "Project";
    private DatabaseReference dbr;
    static Category nullCategory = new Category("", 0);

    public Project(String name, String description, Category[] categories, User author) {
        dbr = FirebaseDatabase.getInstance().getReference(PROJECT_KEY);
        this.name = name;
        this.description = description;
        this.categories = new Category[5];
        for (int i = 0; i < 5; i++) {
            if(i >= categories.length)
                this.categories[i] = nullCategory;
            else
                this.categories[i] = categories[i];
        }
        this.date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        this.author = author;
        //fragment = new ProjectFragment(this);

    }

    @Override
    public String toString() {
        String res = "P [" + name + "] [" + description + "] [" + date + "] [" + author.id + "] [";
        for (int i = 0; i < 5; i++) {
            if(categories[i] != null)
                res += " " + categories[i].name;
        }
        return res + " ]";
    }

    public void save(){
        dbr.push().setValue(this.toString());
    }
}
