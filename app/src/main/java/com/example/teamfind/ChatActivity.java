package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.example.teamfind.databinding.ActivityChatBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbr = FirebaseDatabase.getInstance().getReference("Chats");

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        List<Message> messages = new ArrayList<>();
        String fn = MainActivity.account.getString("first_name", "null") + " " +
                MainActivity.account.getString("second_name", "null");
        Log.d("dfg", fn);

        String[] messagese = getIntent().getStringArrayExtra("messages");
        for (int i = 0; i < messagese.length; i += 3) {
            Message m = new Message(messagese[i], messagese[i + 1], messagese[i + 2]);
            if(m.username.equalsIgnoreCase(fn)){
                messages.add(new Message(messagese[i], messagese[i + 1], messagese[i + 2], true));

            }
            else {
                messages.add(new Message(messagese[i], messagese[i + 1], messagese[i + 2], false));
            }
        }


       /* Message mes = new Message("привет!", "Васёк", true);
        Message mes1 = new Message("привки", "Петька", false);
        Message mes2 = new Message("а наш участковый, ментовская рожа...", "Петька", false);

        messages.add(mes);
        messages.add(mes1);
        messages.add(mes2);*/


        MessageAdapter ma = new MessageAdapter(getApplicationContext(), messages);
        binding.list.setAdapter(ma);

        binding.send.setOnClickListener(view -> {
            messages.add(new Message(binding.write.getText().toString(), fn,
                    new SimpleDateFormat("dd.MM.yyyy").format(new Date())));
            dbr.child(getIntent().getExtras().getString("id")).child("m").setValue(messages);
        });
    }
}