package com.example.teamfind;

import java.util.List;

public class ProjectsLoader {
    public static List<Project> tempProjects; //временный массив для метода load(), пока что без БД
    /*static{
        tempProjects.add(new Project("Приложение для контроля качества сна", "тут большой текст",
                new Category[]{CategoryList.list[1], CategoryList.list[7]}, new User("Кривецкийа")));

        tempProjects.add(new Project("Проргамма для обучения нейросетей", "тут большой текст",
                new Category[]{CategoryList.list[0], CategoryList.list[7], CategoryList.list[12], CategoryList.list[9]},
                new User("Кривецкий ААААААА")));
    }*/
    public static List<Project> load(){
        return tempProjects;
    }
}
