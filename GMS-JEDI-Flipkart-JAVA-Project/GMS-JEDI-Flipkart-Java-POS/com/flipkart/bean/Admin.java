package com.flipkart.bean;

/**
 * Represents an administrator user in the system.
 * Extends the User class.
 */
public class Admin extends User {

    /**
     * Constructs a new Admin user with default values.
     */
    public Admin() {
        super("0", "admin", "admin@flipfit.com", "Admin@123", Role.ADMIN);
    }
}

