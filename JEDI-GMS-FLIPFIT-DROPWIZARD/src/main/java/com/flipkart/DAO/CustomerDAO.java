package com.flipkart.DAO;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.flipkart.constant.SQLConstants.*;

public class CustomerDAO implements CustomerInterfaceDAO {

    public Customer registerCustomer(Customer customer) throws RegistrationFailedException {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);
            stmt.setString(1, customer.getUserID());
            stmt.setString(2, customer.getUserName());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getCustomerPhone());
            stmt.setString(6, customer.getCardDetails());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException exp) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        } catch (Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
        return customer;
    }

    public boolean isUserValid(String userName, String password) throws UserInvalidException {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(CUSTOMER_LOGIN_QUERY);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (SQLException exp) {
            throw new UserInvalidException("User is Invalid. Try again.");
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public Customer getCustomerById(String userName) {
        Customer customer = new Customer();
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customer.setEmail(rs.getString("email"));
            customer.setUserID(rs.getString("Id"));
            customer.setPassword(rs.getString("password"));
            customer.setUserName(rs.getString("name"));
            customer.setCustomerPhone(rs.getString("phone"));
            customer.setCardDetails(rs.getString("cardDetails"));

            stmt.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return customer;
    }

}
