package com.flipkart.business;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerServiceInterface {

    void requestGymOwnerApproval(String gymOwnerId);
    List<GymOwner> viewAllGymOwners();
    GymOwner loginGymOwner(String userId, String password);
    GymOwner registerGymOwner(GymOwner gymOwner);
}
