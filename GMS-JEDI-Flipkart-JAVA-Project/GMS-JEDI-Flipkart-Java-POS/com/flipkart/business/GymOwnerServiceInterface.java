package com.flipkart.business;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerServiceInterface {

    void requestGymOwnerApproval(String gymOwnerId);
    List<GymOwner> viewAllGymOwners();
    boolean loginGymOwner(String userId, String password);

    void registerGymOwner(String userId,String userName, String password, String email, String panNumber,String cardNumber);
}
