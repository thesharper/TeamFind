package com.example.teamfind;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamfind.databinding.FragmentMessageBinding;
import com.example.teamfind.databinding.FragmentMyMessageBinding;
import com.example.teamfind.databinding.FragmentProjectBinding;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.BaseViewHolder> {
    List<Message> list;
    private final LayoutInflater inflater;
    final int MY_MESSAGE = 0;
    final int OWN_MESSAGE = 1;


    public MessageAdapter(Context applicationContext, List<Message> messages) {
        this.inflater = LayoutInflater.from(applicationContext);
        this.list = messages;

    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).my)
            return MY_MESSAGE;
        else
            return OWN_MESSAGE;
    }

    @NonNull
    @Override
    public MessageAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MY_MESSAGE)
            return new BaseViewHolder.MyMessageViewHolder(FragmentMyMessageBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false).getRoot());
        else
            return new BaseViewHolder.OwnMessageViewHolder(FragmentMessageBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(list.get(position));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Message message){

        }

        static class MyMessageViewHolder extends BaseViewHolder{
            private final FragmentMyMessageBinding binding;
            public String text;
            public String name;
            public String date;
            public MyMessageViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = FragmentMyMessageBinding.bind(itemView);
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


        static class OwnMessageViewHolder extends BaseViewHolder{

            private final FragmentMessageBinding binding;
            public String text;
            public String name;
            public String date;
            public OwnMessageViewHolder(@NonNull View itemView) {
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
}
