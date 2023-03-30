package com.example.teamfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    static public SharedPreferences user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbr = FirebaseDatabase.getInstance().getReference();

        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dc : snapshot.child("Chats").getChildren()) {
                        if(snapshot.child("Chats").exists()){

                            Chat c = dc.getValue(Chat.class);
                            Log.d("cykasdfg", MainActivity.account.getString("email", "no"));
                            if(c != null) {
                                if (MainActivity.account.getString("email", "no").equalsIgnoreCase(c.user1) ||
                                        MainActivity.account.getString("email", "no").equalsIgnoreCase(c.user2)) {
                                    c.id = dc.getKey();
                                    chats.add(c);

                                    Log.d("shgdsh", c.id);
                                    Log.d("cyka", c.user1);
                                    Log.d("cyka", String.valueOf(chats.size()));
                                }
                            }
                        }
                    }

                    ChatAdapter ca = new ChatAdapter(getApplicationContext(), chats, new ChatAdapter.OnChatClickListener() {
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


    }
}