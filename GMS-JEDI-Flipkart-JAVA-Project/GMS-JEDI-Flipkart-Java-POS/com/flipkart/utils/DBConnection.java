package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new ExceptionInInitializerError("Could not find JDBC driver. Make sure you have the MySQL Connector/J JAR in your classpath.");
        }
    }

//    public static Connection connect() throws SQLException {
//        if (singleInstance == null || singleInstance.isClosed()) {
//            System.out.println("Creating a new connection to DB....");
//            try {
//                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
//                System.out.println("Database Connected");
//                singleInstance = connection;
//                return connection;
//            } catch (SQLException e) {
//                throw new RuntimeException("Error establishing the database connection", e);
//            }
//        } else {
//            return singleInstance;
//        }
//    }

    public static Connection connect() {
        Connection connection = null;
        if (connection != null)
            return connection;
        else
        {
            try
            {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("./config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("dbdriver");
                String url = prop.getProperty("dburl");
                String user = prop.getProperty("dbuser");
                String password = prop.getProperty("dbpassword");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return connection;
        }

    }
}
