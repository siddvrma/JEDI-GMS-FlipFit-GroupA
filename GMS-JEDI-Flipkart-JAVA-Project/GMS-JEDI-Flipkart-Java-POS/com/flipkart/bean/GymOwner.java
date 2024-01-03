package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {
    private String panNumber;
    private List<String> gymCentreIDs;
    private String cardDetails;
    private int isApproved;

    public GymOwner(String id,String userName, String email, String password, String panNumber, String cardDetails) {
        super(id,userName, email, password, Role.GYMOWNER);
        this.panNumber = panNumber;
        this.cardDetails = cardDetails;
        this.isApproved = 0;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public int isApproved() {
        return isApproved;
    }

    public void setApproved(int approved) {
        isApproved = approved;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public List<String> getGymCentreIDs() {
        return gymCentreIDs;
    }

    public void setGymCentreIDs(List<String> gymCentreIDs) {
        this.gymCentreIDs = gymCentreIDs;
    }

    public void addGymCentreId(String gymCentreId) {
        this.gymCentreIDs.add(gymCentreId);
    }

    public String getCardDetails() { return cardDetails; }

    public void setCardDetails(String cardDetails) { this.cardDetails = cardDetails; }
}
