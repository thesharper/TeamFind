package com.example.teamfind;

import java.util.ArrayList;
import java.util.List;

//класс, экземпляр которого хранит в себе информацию о конкретном экземпляре Project
//в строковом формате
//нужен для сохранения экзмпляра Project в базе данных
public class StringProject {
    public String name;
    public String description;
    public String date;
    public String author;
    public List<String> categories = new ArrayList<>();
    public StringProject(Project p){
        this.name = p.name;
        this.description = p.description;
        this.date = p.date;
        this.author = p.author.id;
        for (int i = 0; i < 5; i++) {
            if(p.categories[i].name != null)
                categories.add(p.categories[i].name);
            else
                categories.add("");
        }
    }
}
