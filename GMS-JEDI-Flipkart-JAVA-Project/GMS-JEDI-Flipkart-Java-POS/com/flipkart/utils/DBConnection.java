package com.flipkart.utils;

import java.sql.*;

public class DBConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/FlipFit";
    static final String USER = "root";
    static final String PASS = "N1g@m75875";
    private static Connection singleInstance = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new ExceptionInInitializerError("Could not find JDBC driver. Make sure you have the MySQL Connector/J JAR in your classpath.");
        }
    }

    public static Connection connect() throws SQLException {
        if (singleInstance == null || singleInstance.isClosed()) {
            System.out.println("Creating a new connection to DB....");
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Database Connected");
                singleInstance = connection;
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException("Error establishing the database connection", e);
            }
        } else {
            return singleInstance;
        }
    }
}
