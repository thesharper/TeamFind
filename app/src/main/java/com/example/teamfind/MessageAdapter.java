package com.example.teamfind;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamfind.databinding.FragmentMessageBinding;
import com.example.teamfind.databinding.FragmentProjectBinding;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    List<Message> list;
    private final LayoutInflater inflater;


    public MessageAdapter(Context applicationContext, List<Message> messages) {
        this.inflater = LayoutInflater.from(applicationContext);
        this.list = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentMessageBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final FragmentMessageBinding binding;
        public String text;
        public String name;
        public String date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = FragmentMessageBinding.bind(itemView);
        }

        public void bind(Message message){
            text = message.text;
            name = message.username;
            date = message.date;


            binding.text.setText(text);
            binding.name.setText(name);
            binding.date.setText(date);
        }
    }


}
