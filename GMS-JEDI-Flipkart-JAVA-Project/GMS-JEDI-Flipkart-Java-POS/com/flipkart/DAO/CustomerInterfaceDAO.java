package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

public interface CustomerInterfaceDAO {

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException;
    public boolean isUserValid(String userName, String password) throws UserInvalidException;
    Customer getCustomerById(String userName);
}
