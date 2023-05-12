package com.example.teamfind.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.teamfind.data.Account;
import com.example.teamfind.data.User;
import com.example.teamfind.databinding.ActivityAuthorizationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthorizationActivity extends AppCompatActivity {
    ActivityAuthorizationBinding binding;
    private DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        SharedPreferences.Editor editor = MainActivity.account.edit();
        editor.putBoolean("isAuth", false);
        editor.apply();


        if(binding.logo != null) {
            FlingAnimation flingAnimation = new FlingAnimation(binding.logo, DynamicAnimation.Y);
            flingAnimation.setStartVelocity(500f);
            flingAnimation.setFriction(0.3f);
            flingAnimation.start();
        }


        binding.login.setOnClickListener(view -> {
            if(!binding.email.getText().toString().equalsIgnoreCase( "") && !binding.password.getText().toString().equalsIgnoreCase("")){
                editor.putString("email", binding.email.getText().toString());
                editor.putString("password", binding.password.getText().toString());
                editor.putString("first_name", binding.firstName.getText().toString());
                editor.putString("second_name", binding.secondName.getText().toString());
                editor.putBoolean("isAuth", true);
                editor.apply();
                startActivity(new Intent(this, MainActivity.class));
            }
        });

        binding.reg.setOnClickListener(view -> {
            if(!binding.email.getText().toString().equalsIgnoreCase( "") &&
                    !binding.password.getText().toString().equalsIgnoreCase("") &&
                    !binding.firstName.getText().toString().equalsIgnoreCase("") &&
                    !binding.secondName.getText().toString().equalsIgnoreCase("")){

                dbr = FirebaseDatabase.getInstance().getReference("Accounts");  //добавление акка
                dbr.push().setValue(new Account(binding.email.getText().toString(),
                        binding.password.getText().toString()));

                dbr = FirebaseDatabase.getInstance().getReference("Users"); //добавление User'а
                dbr.push().setValue(new User(binding.firstName.getText().toString(),
                        binding.secondName.getText().toString(),
                        "id_will_be_soon",
                        binding.email.getText().toString()));

                editor.putString("email", binding.email.getText().toString());
                editor.putString("password", binding.password.getText().toString());
                editor.putString("first_name", binding.firstName.getText().toString());
                editor.putString("second_name", binding.secondName.getText().toString());
                editor.putBoolean("isAuth", true);

                editor.apply();
                startActivity(new Intent(this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

            /*FlingAnimation fa = new FlingAnimation(binding.logo, DynamicAnimation.Y);
            fa.setStartVelocity(-50f);
            fa.setFriction(0.3f);
            fa.start();*/


    }
}