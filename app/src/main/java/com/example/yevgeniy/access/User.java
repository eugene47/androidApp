package com.example.yevgeniy.access;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("token")
    @Expose
    private String token;
    private ArrayList<User> info;
    private String full_name;
    private String username;

    public String getToken() {
        return token;
    }
    public ArrayList<User> getInfo() {
        return info;
    }

    public String getFullName() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }
}