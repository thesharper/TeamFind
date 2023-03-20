package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teamfind.databinding.ActivityNewProjectBinding;

public class NewProjectActivity extends AppCompatActivity {

    ActivityNewProjectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNewProjectBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}