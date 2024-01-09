package com.flipkart.DAO;

import com.flipkart.bean.GymCentre;

import java.util.List;

public interface GymCentreInterfaceDAO {

    List<GymCentre> getAllCentresByOwmerId(String gymOwnerId);
    GymCentre getGymCentreByCentreId(String gymCentreId);
    GymCentre addGymCentre(GymCentre centre);
    public List<GymCentre> getPendingGymCentreList();
    public void validateGymCentre(String gymCentreId, int isApproved);
    public void sendCentreApprovalRequest(String gymCentreId);
    public List<GymCentre> getGymCentreListByCity(String city);
}
