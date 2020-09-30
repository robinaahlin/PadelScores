package com.company.dbhandler;

import com.company.model.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Source: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
 *
 * executeUpdate: Returns an integer representing the number of rows affected by the
 *      SQL statement. Use this method if you are using INSERT, DELETE, or UPDATE SQL statements.
 *
 * executeQuery: Returns one ResultSet object.
 *
 * execute: Returns true if the first object that the query returns is a ResultSet object.
 *      Use this method if the query could return one or more ResultSet objects. Retrieve the ResultSet
 *      objects returned from the query by repeatedly calling Statement.getResultSet.
 */

public class QueryClass {

    private Connection connection;
    private DBConnector dbConnector;

    private static final String GET_ALL_USERS = "SELECT * FROM users;";
    private static final String INSERT_NEW_USER = "INSERT INTO users(username,password) VALUES (?,?);";
    private static final String CHECK_IF_USER_EXIST =
            "SELECT * FROM users WHERE username = ?";

    private List<User> users;

    public QueryClass() {
        dbConnector = new DBConnector();
        connection = dbConnector.getConnection();
    }

    public List<User> getAllUsers() {
        try {
            users = new ArrayList<User>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(String username, String password) {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CHECK_IF_USER_EXIST);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()){
                try {
                    preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.executeUpdate();
                    return true;

                } catch (DataIntegrityViolationException e) {
                    System.out.println("Username already exist!");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
