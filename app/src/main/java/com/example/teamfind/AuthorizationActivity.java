package com.example.teamfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.teamfind.databinding.ActivityAuthorizationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}