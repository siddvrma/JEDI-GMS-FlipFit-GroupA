package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

      // Database credentials
    private static Connection singleInstance = null;
    public static Connection connect() throws SQLException{

        if(singleInstance ==null){
//            System.out.println("Connecting to DB....");
            // Register the jdbc driver
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
//                    String url = "jdbc:mysql://localhost:3306/Flipfit";//flipfit is the name of database 3306 is the port no. of mysql
                FileInputStream inputStream = new FileInputStream("/Users/sukhvir.kaur/JEDI-2.0-Flipkart-Development/GMS-JEDI-Flipkart-JAVA-Project/GMS-JEDI-Flipkart-Java-POS/com/flipkart/config.properties");
                Properties newProp = new Properties();
                newProp.load(inputStream);
                Connection connection = DriverManager.getConnection(newProp.getProperty("url"),newProp.getProperty("user"),newProp.getProperty("password"));
//                System.out.println("Database Connected");
                singleInstance = connection;
                return connection;
            }
            catch (ClassNotFoundException ex){
                System.err.println("Could not find jdbc driver.");
//                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else{
            return singleInstance;
        }

        return null;
    }
}