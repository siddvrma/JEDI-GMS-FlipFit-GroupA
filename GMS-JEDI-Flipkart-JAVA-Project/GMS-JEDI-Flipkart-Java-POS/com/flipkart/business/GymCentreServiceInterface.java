package com.flipkart.business;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

public interface GymCentreServiceInterface {

    List<GymCentre> getAllCentresByOwmerId(String gymOwnerId);
    List<GymCentre> getCentresByCity(String city);
    List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date);
    void addCenter(GymCentre gymCentre);
    void requestGymCentreApproval(String gymCentreId);
    GymCentre getGymCentreById(String centreID);
}
