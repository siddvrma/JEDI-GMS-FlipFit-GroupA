package com.flipkart.client;

import com.flipkart.service.CustomerService;

public class FlipFitCustomerMenu {

    public static void main(String[] args){
        CustomerService customerService = new CustomerService();
        customerService.createCustomer();
        customerService.listCustomer(1);
        System.out.println(customerService.updateCustomer(1));
        System.out.println(customerService.deleteCustomer(1));
    }
}
