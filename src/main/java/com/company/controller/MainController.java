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

        if(loginController.loginUser()){
            System.out.println("Login success!");
            return 1;
        } else {
            System.out.println("Login failed!");
            return 0;
        }
    }
}
