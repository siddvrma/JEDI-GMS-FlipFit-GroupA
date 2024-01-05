package com.flipkart.DAO;

import com.flipkart.DAO.CustomerInterfaceDAO;
import com.flipkart.bean.Customer;
import com.flipkart.connection.DBConnection;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterfaceDAO {
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer (userID, userName, password, email, phoneNumber, cardNumber) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_USERNAME = "SELECT * FROM Customer WHERE userName = ?";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customer WHERE userID = ?";

    private static List<Customer> allCustomers = new ArrayList<>();

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {

            // Assuming you have a method to generate a unique customer ID
            String customerId = generateUniqueCustomerId(userName);

            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, cardNumber);

            preparedStatement.executeUpdate();

            // Create a Customer object
            Customer customer = new Customer(customerId, userName, password, email, phoneNumber, cardNumber);

            // Add the Customer object to the list
            allCustomers.add(customer);

        } catch (SQLException e) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        }
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_USERNAME)) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    return storedPassword.equals(password);
                }
            }

        } catch (SQLException e) {
            throw new UserInvalidException("User is Invalid. Try again.");
        }
        return false;
    }

    public Customer getCustomerById(String userName) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String customerId = resultSet.getString("userID");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String cardNumber = resultSet.getString("cardNumber");

                    return new Customer(customerId, userName, password, email, phoneNumber, cardNumber);
                }
            }

        } catch (SQLException e) {
            // Handle exception or throw a specific exception
        }
        return null;
    }

    private String generateUniqueCustomerId(String userName) {
        // Implement logic to generate a unique customer ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}
