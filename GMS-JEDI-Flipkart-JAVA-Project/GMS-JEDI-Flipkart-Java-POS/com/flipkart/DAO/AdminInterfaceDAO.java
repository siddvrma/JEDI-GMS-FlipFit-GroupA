package com.flipkart.DAO;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.List;

public interface AdminInterfaceDAO {
    List<GymOwner> getPendingGymOwners();
    void validateGymOwner(String gymOwnerId, int isApproved);
    void validateGymCentre(String gymCentreId, int isApproved);
    List<GymCentre> getPendingGymCentres();
}
