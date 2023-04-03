package com.example.teamfind;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    public List<Message> m = new ArrayList<>();
    public String user1;  //emailы юзеров
    public String user2;
    public String id;
    public Chat(){}
    public Chat(String user1, String user2){
        this.user1 = user1;
        this.user2 = user2;
    }
}
