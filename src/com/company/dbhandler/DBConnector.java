package com.company.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection = null;
    private String url = "";

    private final static String user = "root";
    private final static String password = "root1337";

    public DBConnector(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3333/androiddb?autoReconnect=true&useSSL=false", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
