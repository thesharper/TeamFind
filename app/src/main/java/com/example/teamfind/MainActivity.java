package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.teamfind.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        init();
        ProjectAdapter pa = new ProjectAdapter(this, tempProjectsFrags, new ProjectAdapter.OnProjectClickListener() {
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

    void init(){
        dbr = FirebaseDatabase.getInstance().getReference();
        tempProjectsFrags.add(new Project("Программа для обучения нейросетей", "Реализация интерфейса для составление моделей машинного обучения и их обучения",
                new Category[]{CategoryList.list[0]}, new User("ванёк", "t004")));

        tempProjectsFrags.add(new Project("adsfg", "asdfg",
                new Category[]{CategoryList.list[1]}, new User("dsfs", "t003")));
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0], CategoryList.list[5]}, new User("dsfawers", "t002")));
        Project p = new Project("Программа для обучения нейросетей", "Реализация интерфейса для составление моделей машинного обучения и их обучения",
                new Category[]{CategoryList.list[0]}, new User("Кривецкий", "a001"));
        //p.save();
    }
}