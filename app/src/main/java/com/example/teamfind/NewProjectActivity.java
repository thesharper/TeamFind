package com.example.teamfind;

import static com.example.teamfind.MainActivity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamfind.databinding.ActivityNewProjectBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewProjectActivity extends AppCompatActivity {

    ActivityNewProjectBinding binding;
    List<Category> selected_categories = new ArrayList<>();
    Category[] categories = new Category[5];
    private DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNewProjectBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        dbr = FirebaseDatabase.getInstance().getReference();
        init();


        for (int i = 0; i < CategoryList.list.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(CategoryList.list[i].name);
            tv.setBackgroundResource(CategoryList.list[i].drawable_id);
            tv.setTextSize(20);
            tv.setOnClickListener(view -> {
                if(selected_categories.size() < 5) {
                    selected_categories.add(CategoryList.getByName(tv.getText().toString()));
                    if (tv.getParent() != null) {
                        ((ViewGroup) tv.getParent()).removeView(tv);
                    }
                    binding.selectedLayout.addView(tv);
                }
                else{
                    Toast.makeText(this, "Больше нельзя!", Toast.LENGTH_SHORT).show();
                }
            });
            binding.flowLayout.addView(tv);
        }

        for (int i = 0; i < 5; i++) {
            if(i < selected_categories.size())
                categories[i] = selected_categories.get(i);
            else
                categories[i] = CategoryList.nullCategory;
        }

        binding.add.setOnClickListener(view -> {
            Project project = new Project(binding.name.getText().toString(), binding.description.getText().toString(), selected_categories, new User(account.getString("user_name", "").split(" ")[0],
                    account.getString("user_name", "").split(" ")[1], "", account.getString("email", "")));
            project.save();
        });
    }
    void init(){

        binding.userName.setText(account.getString("first_name", "null") + " " +
                account.getString("second_name", "null"));

        binding.mainPage.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

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