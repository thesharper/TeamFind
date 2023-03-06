package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.teamfind.databinding.ActivityMainBinding;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static int[] containers;
    //private static List<Project> tempProjectsFrags;
    private static List<Project> tempProjectsFrags = new ArrayList<>();
    /*static {
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0]}, new User("dsfs")));
        tempProjectsFrags.add(new Project("adsfg", "asdfg",
                new Category[]{CategoryList.list[1]}, new User("dsfs")));
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0], CategoryList.list[5]}, new User("dsfawers")));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        init();
        ProjectAdapter pa = new ProjectAdapter(this, tempProjectsFrags);
        binding.list.setAdapter(pa);
    }
   /* private void fragmentInit() {

        ProjectsLoader pl = new ProjectsLoader();
        List<Project> projects = pl.load();

        for (int i = 0; i < containers.length; i++) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null).add(containers[i],
                    projects.get(i).fragment, String.valueOf(false)).commit();
        }
        binding.rootContainer1.setOnClickListener(view -> projects.get(0).fragment.click());
        binding.rootContainer2.setOnClickListener(view -> projects.get(0).fragment.click());

    }*/
    void init(){
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0]}, new User("dsfs")));
        tempProjectsFrags.add(new Project("adsfg", "asdfg",
                new Category[]{CategoryList.list[1]}, new User("dsfs")));
        tempProjectsFrags.add(new Project("a", "asdfg",
                new Category[]{CategoryList.list[0], CategoryList.list[5]}, new User("dsfawers")));
    }
}