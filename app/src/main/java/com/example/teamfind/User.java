package com.example.teamfind;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String first_name;
    public String second_name;
    public String name;
    public String id;
    //public Project[] projects;
    public String email;

    public User(String first_name, String second_name, String id, String email) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.id = id;
        this.email = email;
        this.name = first_name + " " + second_name;
    }
    public User(){}


    static List<User> r = new ArrayList<>();

    /*static User getById(String id){
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Users");
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        User u = ds.getValue(User.class);
                        Log.d("log", u.email);
                        if (u.email == id)  //email - не null, а name какого то хуя null
                            r.add(u);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(vel);
        Log.d("log", r.get(0).email);
        return null;
    }*/

}
