package com.company.controller;

import com.company.model.User;

import java.util.ArrayList;
import java.util.List;

public class controllerUser {

    private List<User> users;

    public controllerUser(){
        users = new ArrayList<>();
    }

    public void addUser(String name){
        if(name != null) {
            User user = new User(name);
        } else {
            System.out.println("Error, user not added!");
        }
    }
}
