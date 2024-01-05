package com.flipkart.DAOLocal;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterfaceDAO {

    private static List<Customer> allCustomers = new ArrayList<>();

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException {
        try {
            String customerId = generateUniqueCustomerId(userName);
            Customer customer = new Customer(customerId, userName, password, email, phoneNumber, cardNumber);
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
        return null;
    }

    private String generateUniqueCustomerId(String userName) {
        return userName + System.currentTimeMillis();
    }
}
