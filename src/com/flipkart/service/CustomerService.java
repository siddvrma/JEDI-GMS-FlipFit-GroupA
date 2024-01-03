package com.flipkart.service;

public class CustomerService {

    public void createCustomer(){
        System.out.println("Customer Created");
    }

    public Boolean updateCustomer(int id){
        System.out.println("Customer Updated");
        return true;
    }

    public void listCustomer(int id){
        System.out.println("Customer Details");
    }

    public Boolean deleteCustomer(int id){
        System.out.println("Customer Deleted");
        return true;
    }


}
