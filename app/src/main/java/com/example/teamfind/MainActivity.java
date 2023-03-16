package com.example.teamfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.teamfind.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //private static int[] containers;
    //private static List<Project> tempProjectsFrags;
    private static List<Project> tempProjectsFrags = new ArrayList<>();
    private DatabaseReference dbr;
    private String USER_KEY = "User";
    private List<Project> projects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbr = FirebaseDatabase.getInstance().getReference();
        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.child("Projects").getChildren()) { //загрузил проекты
                        StringProject p = ds.getValue(StringProject.class);

                        User author = new User();

                        for (DataSnapshot du : snapshot.child("User").getChildren()) { //загрузил авторов на проекты
                            User user = ds.getValue(User.class);
                            Log.i("inform", user.email);
                            if(user != null) {
                                if (user.email == p.author) {
                                    author = user;
                                }
                            }
                        }

                        Project project = new Project(p.name, p.description, new Category[]{
                                CategoryList.getByName(p.cats[0]),
                                CategoryList.getByName(p.cats[1]),
                                CategoryList.getByName(p.cats[2]),
                                CategoryList.getByName(p.cats[3]),
                                CategoryList.getByName(p.cats[4])}, author);

                        projects.add(project);
                    }

                    for (int i = 0; i < projects.size(); i++) {
                        for (DataSnapshot ds : snapshot.child("User").getChildren()) { //загрузил авторов на проекты
                            User user = ds.getValue(User.class);
                            Log.i("inform", user.email);
                            if(user != null) {
                                if (user.email == projects.get(i).author_id) {
                                    projects.get(i).author = user;
                                }
                            }
                        }
                    }

                    ProjectAdapter pa = new ProjectAdapter(getApplicationContext(), projects, new ProjectAdapter.OnProjectClickListener() {
            @Override
            public void onProjectClick(ProjectAdapter.ViewHolder holder) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт ",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
                intent.putExtra("name", holder.name);
                intent.putExtra("description", holder.description);
                intent.putExtra("author", holder.author);
                intent.putExtra("date", holder.date);

                intent.putExtra("cat1s", holder.cat1.name);
                intent.putExtra("cat1d", holder.cat1.drawable_id);
                intent.putExtra("cat2s", holder.cat2.name);
                intent.putExtra("cat2d", holder.cat2.drawable_id);
                intent.putExtra("cat3s", holder.cat3.name);
                intent.putExtra("cat3d", holder.cat3.drawable_id);
                intent.putExtra("cat4s", holder.cat4.name);
                intent.putExtra("cat4d", holder.cat4.drawable_id);
                intent.putExtra("cat5s", holder.cat5.name);
                intent.putExtra("cat5d", holder.cat5.drawable_id);
                startActivity(intent);
            }
            });
                    binding.list.setAdapter(pa);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(v);
        //Log.i("inform1", String.valueOf(users1[0])); //0

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        init();
        /* ProjectAdapter pa = new ProjectAdapter(this, ProjectsLoader.load(), new ProjectAdapter.OnProjectClickListener() {
            @Override
            public void onProjectClick(ProjectAdapter.ViewHolder holder) {
                Toast.makeText(getApplicationContext(), "Был выбран пункт ",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
                intent.putExtra("name", holder.name);
                intent.putExtra("description", holder.description);
                intent.putExtra("author", holder.author);
                intent.putExtra("date", holder.date);

                intent.putExtra("cat1s", holder.cat1.name);
                intent.putExtra("cat1d", holder.cat1.drawable_id);
                intent.putExtra("cat2s", holder.cat2.name);
                intent.putExtra("cat2d", holder.cat2.drawable_id);
                intent.putExtra("cat3s", holder.cat3.name);
                intent.putExtra("cat3d", holder.cat3.drawable_id);
                intent.putExtra("cat4s", holder.cat4.name);
                intent.putExtra("cat4d", holder.cat4.drawable_id);
                intent.putExtra("cat5s", holder.cat5.name);
                intent.putExtra("cat5d", holder.cat5.drawable_id);
                startActivity(intent);
            }
        });
        binding.list.setAdapter(pa);*/
    }

    void init(){
       // dbr = FirebaseDatabase.getInstance().getReference("Projects");
       /* List<User> users = new ArrayList<>();
        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        User us = ds.getValue(User.class);
                        assert us != null;
                        users.add(us);
                        Log.i("inform", String.valueOf(users.size()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(v);
        Log.i("informmm", String.valueOf(users.size()));*/



        /*tempProjectsFrags.add(new Project("Программа для обучения нейросетей", "Реализация интерфейса для составление моделей машинного обучения и их обучения",
                new Category[]{CategoryList.list[0]}, new User("ванёк","", "t004", "")));

        tempProjectsFrags.add(new Project("adsfg", "asdfg",
                new Category[]{CategoryList.list[1]}, new User("dsfs","", "t003", "")));
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0], CategoryList.list[5]}, new User("dsfawers","", "t002", "")));
        Project p = new Project("Программа для обучения нейросетей", "Реализация интерфейса для составление моделей машинного обучения и их обучения",
                new Category[]{CategoryList.list[0]}, new User("Кривецкий", "", "a001", ""));

        Category[] cs = new Category[]{CategoryList.list[3], CategoryList.list[9], CategoryList.list[12]};*/

        /*User me = new User("Арсений", "Кривецкий", dbr.getKey(), "sreniy06@gmail.com");
        Project p1 = new Project("Приложение для контроля качетсва сна", "большой текст", cs, me);
        Project p2 = new Project("Приложение для контроля диет", "большой текст", cs, me);
        Project p3 = new Project("Игра на Unity", "большой текст", cs, me);
        p1.save();
        p2.save();
        p3.save();*/

        //p.save();
        //DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Users");
        //dbr.push().setValue(new User("Арсений", "Кривецкий", dbr.getKey(), "sreniy06@gmail.com"));
    }
}