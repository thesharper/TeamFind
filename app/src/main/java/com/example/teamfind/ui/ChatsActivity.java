package com.example.teamfind.ui;

import static com.example.teamfind.ui.MainActivity.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.teamfind.data.Chat;
import com.example.teamfind.data.User;
import com.example.teamfind.databinding.ActivityChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {

    ActivityChatsBinding binding;
    private DatabaseReference dbr;
    private List<Chat> chats = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    static public SharedPreferences user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbr = FirebaseDatabase.getInstance().getReference();

        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    chats.clear();
                    names.clear();
                    for(DataSnapshot dc : snapshot.child("Chats").getChildren()) {
                        if(snapshot.child("Chats").exists()){

                            Chat c = dc.getValue(Chat.class);
                            if(c != null) {
                                if (account.getString("email", "no").equalsIgnoreCase(c.user1) ||
                                        account.getString("email", "no").equalsIgnoreCase(c.user2)) {
                                    c.id = dc.getKey();

                                    //поиск полных имен пользователей для отображения названия чата
                                    for(DataSnapshot ds : snapshot.child("Users").getChildren()){
                                        if(snapshot.child("Users").exists()){
                                            User r = ds.getValue(User.class);

                                            if(r != null) {

                                                if ((c.user1.equalsIgnoreCase(r.email) || c.user2.equalsIgnoreCase(r.email)) &&
                                                        !r.email.equalsIgnoreCase(account.getString("email", "no"))) {
                                                    names.add(r.name);

                                                }
                                            }
                                        }
                                    }

                                    chats.add(c);
                                }
                            }
                        }
                    }

                    ChatAdapter ca = new ChatAdapter(getApplicationContext(), chats, names, new ChatAdapter.OnChatClickListener() {
                        @Override
                        public void onChatClick(ChatAdapter.ViewHolder holder) {
                            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);

                            String[] everything = new String[holder.messages.size() * 3];
                            for (int i = 0; i < holder.messages.size(); i++) {
                                everything[i * 3] = holder.messages.get(i).text;
                                everything[i * 3 + 1] = holder.messages.get(i).username;
                                everything[i * 3 + 2] = holder.messages.get(i).date;
                            }

                            intent.putExtra("messages", everything);
                            intent.putExtra("id", holder.id);
                            intent.putExtra("username", holder.name);


                            startActivity(intent);
                        }
                    });

                    binding.list.setAdapter(ca);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbr.addValueEventListener(v);

        binding = ActivityChatsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        user = getSharedPreferences("User", MODE_PRIVATE);
        init();


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

        binding.mainPage.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        binding.opensidebar.setOnClickListener(view -> {
            binding.drawer.open();
        });
    }
}