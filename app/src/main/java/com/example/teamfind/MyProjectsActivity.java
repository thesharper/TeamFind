package com.example.teamfind;

import static com.example.teamfind.MainActivity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.teamfind.databinding.ActivityMyProjectsBinding;
import com.example.teamfind.databinding.ActivityProjectBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyProjectsActivity extends AppCompatActivity {
    ActivityMyProjectsBinding binding;
    private DatabaseReference dbr;
    private List<Project> projects = new ArrayList<>();
    static public SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMyProjectsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        user = getSharedPreferences("User", MODE_PRIVATE);
        init();

        dbr = FirebaseDatabase.getInstance().getReference();
        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User us = new User();
                    SharedPreferences.Editor editor = user.edit();
                    for(DataSnapshot du : snapshot.child("User").getChildren()) {
                        if(snapshot.child("Users").exists()){
                            User u = du.getValue(User.class);
                            if(u.email.equalsIgnoreCase(account.getString("email", "no"))){
                                us = u;
                                editor.putString("name", us.name);
                                editor.putString("email", us.email);
                            }
                        }
                    }
                    editor.apply();


                    for(DataSnapshot ds : snapshot.child("Projects").getChildren()){
                        StringProject project = ds.getValue(StringProject.class);
                        if(project.author.equalsIgnoreCase(account.getString("email", "no"))){
                            Project p = new Project(project.name, project.description, new Category[]{
                                    CategoryList.getByName(project.categories.get(0)),
                                    CategoryList.getByName(project.categories.get(1)),
                                    CategoryList.getByName(project.categories.get(2)),
                                    CategoryList.getByName(project.categories.get(3)),
                                    CategoryList.getByName(project.categories.get(4))}, us, project.date);
                            projects.add(p);
                        }
                    }

                    ProjectAdapter pa = new ProjectAdapter(getApplicationContext(), projects, new ProjectAdapter.OnProjectClickListener() {
                        @Override
                        public void onProjectClick(ProjectAdapter.ViewHolder holder) {
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
    }
    void init(){

        binding.userName.setText(account.getString("first_name", "null") + " " +
                account.getString("second_name", "null"));

        binding.exit.setOnClickListener(view -> {
            SharedPreferences.Editor editor = account.edit();
            editor.putString("password", "");
            editor.putString("email", "");
            editor.putBoolean("isAuth", false);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), AuthorizationActivity.class));
        });

        binding.myProjects.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MyProjectsActivity.class));
        });

        binding.newProject.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), NewProjectActivity.class));
        });

        binding.about.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ChatActivity.class));
        });
        binding.chats.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ChatsActivity.class));
        });
        binding.mainPage.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}