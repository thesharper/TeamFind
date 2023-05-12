package com.example.teamfind.data;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class Account {
    public String email;
    public String password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Account(){}

}
