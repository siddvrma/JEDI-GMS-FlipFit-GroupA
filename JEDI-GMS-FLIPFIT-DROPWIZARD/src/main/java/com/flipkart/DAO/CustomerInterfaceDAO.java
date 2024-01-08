package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

public interface CustomerInterfaceDAO {

    public Customer registerCustomer(Customer customer) throws RegistrationFailedException;
    public boolean isUserValid(String userName, String password) throws UserInvalidException;
    Customer getCustomerById(String userName);
}
