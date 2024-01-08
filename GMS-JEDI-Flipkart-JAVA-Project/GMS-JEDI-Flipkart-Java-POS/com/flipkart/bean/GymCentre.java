package com.flipkart.bean;

import java.util.List;

/**
 * Represents a gym center in the system.
 */
public class GymCentre {

    //    INSERT INTO FlipFit.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved)
    /**
     * The unique identifier for the gym center.
     */
    private String gymCentreID;

    /**
     * The owner ID associated with the gym center.
     */
    private String ownerID;

    /**
     * The name of the gym center.
     */
    private String gymCenterName;

    /**
     * The GST identification number of the gym center.
     */
    protected String gstin;

    /**
     * The city where the gym center is located.
     */
    private String city;

    /**
     * The capacity of the gym center.
     */
    private int capacity;

    /**
     * The price associated with the gym center.
     */
    private int price;

    /**
     * The approval status of the gym center.
     */
    private int isApproved;

    /**
     * Default constructor for the GymCentre class.
     */
    public GymCentre() {

    }

    /**
     * Constructs a new gym center with the given details.
     *
     * @param gymCentreID   The unique identifier for the gym center.
     * @param ownerID       The owner ID associated with the gym center.
     * @param gymCenterName The name of the gym center.
     * @param gstin         The GST identification number of the gym center.
     * @param city          The city where the gym center is located.
     * @param capacity      The capacity of the gym center.
     * @param price         The price associated with the gym center.
     * @param isApproved    The approval status of the gym center.
     */
    public GymCentre(String gymCentreID, String ownerID, String gymCenterName, String gstin, String city, int capacity, int price, int isApproved) {
        this.gymCentreID = gymCentreID;
        this.ownerID = ownerID;
        this.gymCenterName = gymCenterName;
        this.gstin = gstin;
        this.city = city;
        this.capacity = capacity;
        this.price = price;
        this.isApproved = isApproved;
    }

    /**
     * Constructs a new gym center with the given details.
     *
     * @param gymCentreID The unique identifier for the gym center.
     * @param ownerID     The owner ID associated with the gym center.
     * @param gymCenterName The name of the gym center.
     * @param city          The city where the gym center is located.
     * @param approved      The approval status of the gym center.
     */
    public GymCentre(String gymCentreID, String ownerID, String gymCenterName, String city, int approved) {
        this.gymCentreID = gymCentreID;
        this.ownerID = ownerID;
        this.gymCenterName = gymCenterName;
        this.city = city;
        this.isApproved = approved;
    }

    /**
     * Overrides the toString method to provide a string representation of the gym center.
     *
     * @return A string representation of the gym center.
     */
    @Override
    public String toString() {
        return "GymCentre{" +
                "gymCentreID='" + gymCentreID + '\'' +
                ", gymCenterName='" + gymCenterName + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    /**
     * Constructs a new gym center with the given details, setting approval status to 0.
     *
     * @param gymCentreID The unique identifier for the gym center.
     * @param ownerID     The owner ID associated with the gym center.
     * @param gymCenterName The name of the gym center.
     * @param gstin       The GST identification number of the gym center.
     * @param city        The city where the gym center is located.
     * @param capacity    The capacity of the gym center.
     * @param price       The price associated with the gym center.
     */
    public GymCentre(String gymCentreID, String ownerID, String gymCenterName, String gstin, String city, int capacity, int price) {
        this.gymCentreID = gymCentreID;
        this.ownerID = ownerID;
        this.gymCenterName = gymCenterName;
        this.gstin = gstin;
        this.city = city;
        this.capacity = capacity;
        this.price = price;
        this.isApproved = 0;
    }

    /**
     * Gets the GST identification number of the gym center.
     *
     * @return The GST identification number.
     */
    public String getGstin() {
        return gstin;
    }

    /**
     * Sets the GST identification number of the gym center.
     *
     * @param gstin The GST identification number.
     */
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    /**
     * Gets the price associated with the gym center.
     *
     * @return The price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price associated with the gym center.
     *
     * @param price The price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the unique identifier for the gym center.
     *
     * @return The gym center ID.
     */
    public String getGymCentreID() {
        return gymCentreID;
    }

    /**
     * Sets the unique identifier for the gym center.
     *
     * @param gymCentreID The gym center ID.
     */
    public void setGymCentreID(String gymCentreID) {
        this.gymCentreID = gymCentreID;
    }

    /**
     * Gets the city where the gym center is located.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the gym center is located.
     *
     * @param city The city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the capacity of the gym center.
     *
     * @return The capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the gym center.
     *
     * @param capacity The capacity.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the name of the gym center.
     *
     * @return The gym center name.
     */
    public String getGymCenterName() {
        return gymCenterName;
    }

    /**
     * Sets the name of the gym center.
     *
     * @param gymCenterName The gym center name.
     */
    public void setGymCenterName(String gymCenterName) {
        this.gymCenterName = gymCenterName;
    }

    /**
     * Gets the owner ID associated with the gym center.
     *
     * @return The owner ID.
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * Sets the owner ID associated with the gym center.
     *
     * @param ownerID The owner ID.
     */
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * Gets the approval status of the gym center.
     *
     * @return The approval status.
     */
    public int isApproved() {
        return isApproved;
    }

    /**
     * Sets the approval status of the gym center.
     *
     * @param approved The approval status.
     */
    public void setApproved(int approved) {
        isApproved = approved;
    }
}
