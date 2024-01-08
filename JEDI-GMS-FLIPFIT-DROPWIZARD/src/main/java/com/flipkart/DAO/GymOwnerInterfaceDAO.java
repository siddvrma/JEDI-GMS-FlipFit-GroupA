package com.flipkart.DAO;

import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerInterfaceDAO {

    public List<GymOwner> getGymOwnerList();
    public void setGymOwnerList(List<GymOwner> gymOwnerList);
    public GymOwner registerGymOwner(GymOwner gymOwner);
    public List<GymOwner> getPendingGymOwnerList();
    public void sendOwnerApprovalRequest(String gymOwnerId);
    public void setPendingGymOwnerList();
    public void validateGymOwner(String gymOwnerId, int isApproved);

}
