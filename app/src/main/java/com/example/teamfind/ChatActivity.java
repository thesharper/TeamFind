package com.example.teamfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.example.teamfind.databinding.ActivityChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    private DatabaseReference dbr;
    private DatabaseReference dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Message> messages = new ArrayList<>();

        String fn = MainActivity.account.getString("first_name", "null") + " " +
                MainActivity.account.getString("second_name", "null");

        dbr = FirebaseDatabase.getInstance().getReference("Chats");
        dbm = FirebaseDatabase.getInstance().getReference("Chats").child(getIntent().getExtras().getString("id"));

        ValueEventListener v = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    messages.clear();
                    for(DataSnapshot ds : snapshot.child("m").getChildren()){
                        Message m = ds.getValue(Message.class);
                        if(m != null) {
                            if(m.username.equalsIgnoreCase(fn))
                                m.my = true;
                            else
                                m.my = false;

                            messages.add(m);
                        }
                    }
                }
                MessageAdapter ma = new MessageAdapter(getApplicationContext(), messages);
                binding.list.setAdapter(ma);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbm.addValueEventListener(v);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        Log.d("dfg", fn);

        String[] messagese = getIntent().getStringArrayExtra("messages");
        /*for (int i = 0; i < messagese.length; i += 3) {
            Message m = new Message(messagese[i], messagese[i + 1], messagese[i + 2]);
            if(m.username.equalsIgnoreCase(fn)){
                messages.add(new Message(messagese[i], messagese[i + 1], messagese[i + 2], true));

            }
            else {
                messages.add(new Message(messagese[i], messagese[i + 1], messagese[i + 2], false));
            }
        }*/




        MessageAdapter ma = new MessageAdapter(getApplicationContext(), messages);
        //binding.list.setAdapter(ma);

        binding.send.setOnClickListener(view -> {
            messages.add(new Message(binding.write.getText().toString(), fn,
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date())));
            dbr.child(getIntent().getExtras().getString("id")).child("m").setValue(messages);
            binding.write.setText("");
        });
    }
}