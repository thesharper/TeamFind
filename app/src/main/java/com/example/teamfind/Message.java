package com.example.teamfind;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    public String text;
    public String username;
    public String date;
    public boolean my;
    public Message(String text, String username, boolean my){
        this.text = text;
        this.username = username;
        this.date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        this.my = my;
    }

    public Message(String text){
        this.text = text;
        this.date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }
    public Message(String text, String username, String date){
        this.text = text;
        this.username = username;
        this.date = date;
    }
    public Message(){}
    public Message(String text, String username, String date, boolean my){
        this.text = text;
        this.username = username;
        this.date = date;
        this.my = my;
    }
}
