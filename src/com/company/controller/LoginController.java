package com.company.controller;

import com.company.dbhandler.DBConnector;
import com.company.dbhandler.QueryClass;
import com.company.model.User;

import java.sql.Connection;
import java.util.List;

public class LoginController {

    private Connection connection = null;

    public LoginController(){
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.getConnection();
    }

    // Database queries missing for bean to work properly
    public int loginUser(){
        return loginUser(LoginBean.getUsername(), LoginBean.getPassword());
    }

    /**
     * Fix query-class for making database requests.
     */
    /*
    private int loginUser(String username, String password){
        QueryClass queryClass = new QueryClass();
        List<User> users = queryClass.getAllUsers();

        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user.getUserID();
            }
        }
        return 0;
    }
     */
}