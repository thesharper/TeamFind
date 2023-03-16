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
    public String DBid;
    //public ProjectFragment fragment;
    private String PROJECT_KEY = "Projects";
    private DatabaseReference dbr;
    public String author_id;
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
    public Project(String name, String description, Category[] categories, String author_id) {
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
        this.author_id = author_id;
        //fragment = new ProjectFragment(this);

    }

   /* @Override
    public String toString() {
        String res = "P [" + name + "] [" + description + "] [" + date + "] [" + author.id + "] [";
        for (int i = 0; i < 5; i++) {
            if(categories[i] != null)
                res += " " + categories[i].name;
        }
        return res + " ]";
    }*/

    public void save(){
        String id = dbr.getKey();
        this.DBid = id;
        dbr.push().setValue(new StringProject(this));
    }
    public static Project getByString(StringProject sp){
        Category[] cats = new Category[5];
        for (int i = 0; i < 5; i++) {
            if(sp.categories.get(i) != "")
                cats[i] = CategoryList.getByName(sp.categories.get(i));
            else
                cats[i] = nullCategory;
        }
        return new Project(sp.name, sp.description, cats, sp.author);
    }
}
