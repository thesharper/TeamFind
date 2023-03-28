package com.example.teamfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

import com.example.teamfind.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    Chat tempChat = new Chat();
//    Display display = getWindowManager().getDefaultDisplay();
    DisplayMetrics displaymetrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Message mes = new Message("привет!", "Васёк");
        Message mes1 = new Message("привки", "Петька");
        Message mes2 = new Message("а наш участковый, ментовская рожа...", "Петька");
        List<Message> messages = new ArrayList<>();
        messages.add(mes);
        messages.add(mes1);
        messages.add(mes2);


        MessageAdapter ma = new MessageAdapter(getApplicationContext(), messages);
        binding.list.setAdapter(ma);
    }
}