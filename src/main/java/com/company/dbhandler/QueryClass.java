package com.company.dbhandler;

import java.sql.Connection;

public class QueryClass {

    private Connection connection;
    private DBConnector dbConnector;

    public QueryClass() {
        dbConnector = new DBConnector();
        connection = dbConnector.getConnection();
    }
}
