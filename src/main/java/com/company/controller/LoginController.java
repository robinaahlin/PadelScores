package com.company.controller;

import com.company.dbhandler.DBConnector;
import com.company.dbhandler.QueryClass;
import com.company.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    private Connection connection;

    private static final String AUTHENTICATE_USER = "SELECT * FROM padeldb.users WHERE username = ? AND password = ?;";

    public LoginController(){
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.getConnection();
    }

    // Database queries missing for bean to work properly
    public boolean loginUser(){
        return loginUser(LoginBean.getUsername(), LoginBean.getPassword());
    }

    /**
     * Fix query-class for making database requests.
     */
    private boolean loginUser(String username, String password){
        return authenticateUser(username,password);
    }

    private boolean authenticateUser(String username, String password) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("username");
                System.out.println("ResultSet: " + name);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}