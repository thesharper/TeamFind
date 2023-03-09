package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teamfind.databinding.ActivityProjectBinding;

public class ProjectActivity extends AppCompatActivity {
    ActivityProjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText(getIntent().getExtras().get("name").toString());
        binding.description.setText(getIntent().getExtras().get("description").toString());
        binding.author.setText(getIntent().getExtras().get("author").toString());
        binding.date.setText(getIntent().getExtras().get("date").toString());

        binding.cat1.setText(getIntent().getExtras().get("cat1s").toString());
        binding.cat1.setBackgroundResource((int)getIntent().getExtras().get("cat1d"));
        binding.cat2.setText(getIntent().getExtras().get("cat2s").toString());
        binding.cat2.setBackgroundResource((int)getIntent().getExtras().get("cat2d"));
        binding.cat3.setText(getIntent().getExtras().get("cat3s").toString());
        binding.cat3.setBackgroundResource((int)getIntent().getExtras().get("cat3d"));
        binding.cat4.setText(getIntent().getExtras().get("cat4s").toString());
        binding.cat4.setBackgroundResource((int)getIntent().getExtras().get("cat4d"));
        binding.cat5.setText(getIntent().getExtras().get("cat5s").toString());
        binding.cat5.setBackgroundResource((int)getIntent().getExtras().get("cat5d"));
    }
}