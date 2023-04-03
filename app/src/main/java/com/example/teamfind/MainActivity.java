package com.example.teamfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
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
    private DatabaseReference dbr;
    private List<Project> projects = new ArrayList<>();
    public static SharedPreferences account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        account = getSharedPreferences("Account", MODE_PRIVATE);
        SharedPreferences.Editor editor = account.edit();
        dbr = FirebaseDatabase.getInstance().getReference();

        if(!account.getBoolean("isAuth", false))
            startActivity(new Intent(this, AuthorizationActivity.class));


        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User cu = new User();
                    for (DataSnapshot da : snapshot.child("Accounts").getChildren()) {
                        Account acc = da.getValue(Account.class);
                        Log.d("account email", acc.email);
                        Log.d("shared pref email", account.getString("password", "no"));
                        if (acc.password.equalsIgnoreCase(account.getString("password", ""))
                                && acc.email.equalsIgnoreCase(account.getString("email", ""))) {
                            Log.d("information", acc.email);
                            for (DataSnapshot dsu : snapshot.child("Users").getChildren()) {
                                if (snapshot.child("Users").exists()) {
                                    cu = dsu.getValue(User.class);
                                    Log.d("current user email", cu.email);
                                    if (cu.email.equalsIgnoreCase(account.getString("email", ""))) {
                                        User.thisUser = cu;
                                        //binding.userName.setText(cu.name);
                                    }
                                }
                            }
                        }
                    }
                    if(User.thisUser == null){ //не авторизован
                        Toast.makeText(getApplicationContext(), "Неверные данные!", Toast.LENGTH_SHORT).show();
                        editor.putBoolean("isAuth", false);
                        startActivity(new Intent(getApplicationContext(), AuthorizationActivity.class));
                    }
                    else{
                        editor.putString("first_name", User.thisUser.first_name);
                        editor.putString("second_name", User.thisUser.second_name);
                        editor.putBoolean("isAuth", true);
                    }

                    binding.userName.setText(account.getString("first_name", "null") + " " +
                            account.getString("second_name", "null"));

                    projects.clear();
                    for (DataSnapshot ds : snapshot.child("Projects").getChildren()) {
                        StringProject p = ds.getValue(StringProject.class);


                        User author = new User();

                        for (DataSnapshot du : snapshot.child("Users").getChildren()) {
                            if (snapshot.child("Users").exists()) {
                                User user = du.getValue(User.class);
                                if (user != null) {
                                    if (user.email.equalsIgnoreCase(p.author)) {
                                        author = new User(user.first_name, user.second_name, user.id, user.email);
                                    }
                                }
                            }
                        }
                        Project project = new Project(p.name, p.description, new Category[]{
                                CategoryList.getByName(p.categories.get(0)),
                                CategoryList.getByName(p.categories.get(1)),
                                CategoryList.getByName(p.categories.get(2)),
                                CategoryList.getByName(p.categories.get(3)),
                                CategoryList.getByName(p.categories.get(4))}, author, p.date);
                        projects.add(project);
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
                    //binding.userName.setText(cu.name);
                    editor.putString("user_name", cu.name);
                    binding.list.setAdapter(pa);
                    editor.apply();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        dbr.addValueEventListener(v);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        init();
    }

    void init(){

        binding.userName.setText(account.getString("user_name", "not stated"));

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
    }
}