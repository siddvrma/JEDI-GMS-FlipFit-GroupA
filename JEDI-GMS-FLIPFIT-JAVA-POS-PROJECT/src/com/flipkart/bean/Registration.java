package com.flipkart.bean;

public class Registration {
    private int userId;
    private String name;
    private long phone;
    private int location;
    private int password;
    private Role role;

    public Registration(int userId, String name, long phone, int location, int password, Role role) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.password = password;
        this.role = role;
    }

    // Getter methods

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public int getLocation() {
        return location;
    }

    public int getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // Setter methods

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

