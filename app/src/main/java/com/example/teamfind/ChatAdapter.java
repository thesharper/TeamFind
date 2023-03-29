package com.example.teamfind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamfind.databinding.FragmentChatBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    List<Chat> list;
    private final LayoutInflater inflater;
    interface OnChatClickListener{
        void onChatClick(ViewHolder holder);
    }
    private final OnChatClickListener clickListener;

    public ChatAdapter(Context context, List<Chat> chats, OnChatClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.list = chats;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentChatBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onChatClick(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        public String name;
        public List<Message> messages;

        private final FragmentChatBinding itemBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = FragmentChatBinding.bind(itemView);
        }
        public void bind(Chat chat){
            name = chat.user1;
            messages = chat.m;
            itemBinding.name.setText(name);
        }
    }
}
