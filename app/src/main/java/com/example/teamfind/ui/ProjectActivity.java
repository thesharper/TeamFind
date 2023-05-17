package com.example.teamfind.ui;

import static com.example.teamfind.ui.MainActivity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.teamfind.R;
import com.example.teamfind.data.Chat;
import com.example.teamfind.data.Message;
import com.example.teamfind.data.StringProject;
import com.example.teamfind.data.User;
import com.example.teamfind.databinding.ActivityProjectBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProjectActivity extends AppCompatActivity {
    ActivityProjectBinding binding;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*if(account.getString("email", "").equalsIgnoreCase(getIntent().getExtras().get("author").toString())) {
            binding.respond.setText("Удалить проект");
            binding.respond.setOnClickListener(view -> {
                DatabaseReference ref = dbr.push().child("Projects");
                ref.child(getIntent().getExtras().getString("id")).removeValue();
            });
        }*/
        Log.d("me", account.getString("email", ""));
        Log.d("not me", getIntent().getExtras().get("author").toString());



        dbr = FirebaseDatabase.getInstance().getReference();
        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    binding.name.setText(getIntent().getExtras().get("name").toString());
                    binding.description.setText(getIntent().getExtras().get("description").toString());
                    binding.author.setText(getIntent().getExtras().get("author").toString());
                    binding.date.setText(getIntent().getExtras().get("date").toString());

                    binding.cat1.setText(getIntent().getExtras().get("cat1s").toString());
                    binding.cat1.setBackgroundResource((int) getIntent().getExtras().get("cat1d"));
                    binding.cat2.setText(getIntent().getExtras().get("cat2s").toString());
                    binding.cat2.setBackgroundResource((int) getIntent().getExtras().get("cat2d"));
                    binding.cat3.setText(getIntent().getExtras().get("cat3s").toString());
                    binding.cat3.setBackgroundResource((int) getIntent().getExtras().get("cat3d"));
                    binding.cat4.setText(getIntent().getExtras().get("cat4s").toString());
                    binding.cat4.setBackgroundResource((int) getIntent().getExtras().get("cat4d"));
                    binding.cat5.setText(getIntent().getExtras().get("cat5s").toString());
                    binding.cat5.setBackgroundResource((int) getIntent().getExtras().get("cat5d"));

                    if(!(account.getString("first_name", "") + " " + account.getString("second_name", "")).equalsIgnoreCase(getIntent().getExtras().get("author").toString())) {
                        binding.respond.setOnClickListener(view -> {
                            if (snapshot.child("Projects").exists()) {
                                String username;
                                for (DataSnapshot dp : snapshot.child("Projects").getChildren()) {
                                    StringProject sp = dp.getValue(StringProject.class);
                                    if (getIntent().getExtras().get("name").toString().equalsIgnoreCase(sp.name)) {
                                        username = sp.author;
                                        Chat c = new Chat(account.getString("email", "no"), username);
                                        boolean this_chat_exist = false;

                                        if (snapshot.child("Chats").exists()) {
                                            for (DataSnapshot chat : snapshot.child("Chats").getChildren()) {
                                                Chat schat = chat.getValue(Chat.class);

                                                if (schat != null) {
                                                    if (schat.user1 != null && schat.user2 != null) {
                                                        if ((schat.user1.equalsIgnoreCase(c.user1) || schat.user1.equalsIgnoreCase(c.user2)) &&
                                                                (schat.user2.equalsIgnoreCase(c.user1) || schat.user2.equalsIgnoreCase(c.user2))) {
                                                            this_chat_exist = true;
                                                            c = schat;
                                                            c.id = chat.getKey();
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        String name = "";
                                        if (snapshot.child("Users").exists()) {
                                            for (DataSnapshot suser : snapshot.child("Users").getChildren()) {
                                                User user = suser.getValue(User.class);
                                                if (user != null) {
                                                    if (user.email.equalsIgnoreCase(username))
                                                        name = user.name;
                                                }
                                            }
                                        }

                                        if (!this_chat_exist) {
                                            DatabaseReference ref = dbr.child("Chats").push();
                                            String key = ref.getKey();
                                            ref.setValue(c);


                                            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                                            intent.putExtra("messages", new Message[]{});
                                            intent.putExtra("id", key);
                                            intent.putExtra("username", name);
                                            startActivity(intent);
                                        } else {
                                            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                                            intent.putExtra("messages", c.m.toArray());
                                            intent.putExtra("id", c.id);
                                            intent.putExtra("username", name);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(v);

        super.onCreate(savedInstanceState);
        binding = ActivityProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(account.getString("email", "no").equalsIgnoreCase(getIntent().getExtras().getString("author_id"))) {
            binding.respond.setText("Удалить проект");

            binding.respond.setOnClickListener(view -> {
                dbr.child("Projects").child(getIntent().getExtras().getString("id")).removeValue();
                Toast.makeText(getApplicationContext(), R.string.proj_deleted, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MyProjectsActivity.class));
            });
        }

        binding.back.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

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