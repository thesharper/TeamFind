package com.example.teamfind;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProjectsLoader {
    //public static List<Project> tempProjects;
    static List<Project> list = new ArrayList<>();
    static DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Projects");
    public static List<Project> load(){


        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        StringProject p = ds.getValue(StringProject.class);
                        Log.d("logi", p.author);
                        list.add(Project.getByString(p));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(vel);

        Log.d("log", list.get(0).name);
        return list;
    }
    static void getFromDB(){
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    StringProject p = ds.getValue(StringProject.class);
                    Log.d("logi", p.author);
                    list.add(Project.getByString(p));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(vel);

        Log.d("log", list.get(0).name);
    }
}
