package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterfaceDAO {

    private List<Customer> allCustomers = new ArrayList<>();

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try {
            // Assuming you have a method to generate a unique customer ID
            String customerId = generateUniqueCustomerId(userName);

            // Create a Customer object
            Customer customer = new Customer(customerId, userName, password, email, phoneNumber, cardNumber);

            // Add the Customer object to the list
            allCustomers.add(customer);
        } catch (Exception e) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        }
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        throw new UserInvalidException("User is Invalid. Try again.");
    }

    public Customer getCustomerById(String userName) {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        return null; // or throw an exception if not found
    }

    private String generateUniqueCustomerId(String userName) {
        // Implement logic to generate a unique customer ID based on your requirements
        return userName + System.currentTimeMillis();
    }
}
