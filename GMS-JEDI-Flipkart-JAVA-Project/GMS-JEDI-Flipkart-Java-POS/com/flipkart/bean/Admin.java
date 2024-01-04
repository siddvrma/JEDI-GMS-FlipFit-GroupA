package com.flipkart.bean;

public class Admin extends User {

    public Admin() {
        super("0","admin", "admin@flipfit.com", "Admin@123", Role.ADMIN);
    }
}
