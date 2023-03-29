package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.example.teamfind.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }
}