package com.flipkart.bean;


/**
 * Represents a user in the system.
 */
public class User {

    /**
     * The unique identifier for the user.
     */
    private String userID;

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The role of the user (e.g., ADMIN, GYMOWNER, CUSTOMER).
     */
    private Role role;

    /**
     * Constructs a new user with the provided user ID, username, email, password, and role.
     *
     * @param id       The unique identifier for the user.
     * @param userName The username of the user.
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @param role     The role of the user.
     */
    public User(String id, String userName, String email, String password, Role role) {
        this.userID = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the role of the user.
     *
     * @return The role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The role of the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userID The user ID.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     *
     * @param userName The username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
