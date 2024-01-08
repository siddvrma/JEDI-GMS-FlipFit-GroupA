package com.flipkart.bean;

public class GymCentre {

    //    INSERT INTO FlipFit.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved)
    private String gymCentreID;
    private String ownerID;
    private String gymCenterName;
    protected String gstin;
    private String city;
    private int capacity;
    private int price;
    private int isApproved;

    public GymCentre() {

    }


    @Override
    public String toString() {
        return "GymCentre{" +
                "gymCentreID='" + gymCentreID + '\'' +
                ", gymCenterName='" + gymCenterName + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    //BAD VOCABULARY
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

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGymCentreID() {
        return gymCentreID;
    }

    public void setGymCentreID(String gymCentreID) {
        this.gymCentreID = gymCentreID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getGymCenterName() {
        return gymCenterName;
    }

    public void setGymCenterName(String gymCenterName) {
        this.gymCenterName = gymCenterName;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public int isApproved() {
        return isApproved;
    }

    public void setApproved(int approved) {
        isApproved = approved;
    }
}
