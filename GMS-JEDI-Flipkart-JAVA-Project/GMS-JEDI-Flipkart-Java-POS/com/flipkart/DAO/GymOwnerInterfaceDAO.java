package com.flipkart.DAO;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerInterfaceDAO {
    List<GymOwner> getGymOwnerList();
    void setGymOwnerList(List<GymOwner> gymOwnerList);
    void registerGymOwner(GymOwner gymOwner);
    List<GymOwner> getPendingGymOwnerList();
    void sendOwnerApprovalRequest(String gymOwnerId);
    void setPendingGymOwnerList();
    void validateGymOwner(String gymOwnerId, int isApproved);
}
