package com.company.controller;

import com.company.dbhandler.QueryClass;

import java.util.List;

public class MainController {

    private String username;
    private String password;

    private QueryClass queryClass;

    public MainController(){
        queryClass = new QueryClass();
    }

    public int loginUser(){
        LoginController loginController = new LoginController();
        int userID = loginController.loginUser();

        if(userID != 0){
            System.out.println("Login success!");
        } else {
            System.out.println("Login failed!");
        }
        return userID;
    }

    // Create a new user with input from user.
    public void createNewUser(){
        queryClass.addUser(LoginBean.getUsername(), LoginBean.getPassword());
    }
}
