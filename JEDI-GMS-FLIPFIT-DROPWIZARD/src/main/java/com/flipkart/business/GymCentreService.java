package com.flipkart.business;

import com.flipkart.DAO.GymCentreDAO;
import com.flipkart.DAO.GymCentreInterfaceDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

public class GymCentreService implements GymCentreServiceInterface {

    private static GymCentreInterfaceDAO gymCentreDAO = new GymCentreDAO();
    private static ScheduleServiceInterface scheduleService = new ScheduleService();

    public List<GymCentre> getAllCentresByOwmerId(String gymOwnerId) {
        return gymCentreDAO.getAllCentresByOwmerId(gymOwnerId);
    }

    public List<GymCentre> getCentresByCity(String city){
        return gymCentreDAO.getGymCentreListByCity(city);
    }

    public List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date){
        return scheduleService.getAllAvailableSlotsByDate(centreID, date);
    }

    public GymCentre addCenter(GymCentre gymCentre) {
        //takes gymCenter details
        return gymCentreDAO.addGymCentre(gymCentre);

    }

    public boolean isValdidCentre(String centreID){
        if(getGymCentreById(centreID) == null) return false;
        return true;

    }

    public void requestGymCentreApproval(String gymCentreId){
        gymCentreDAO.sendCentreApprovalRequest(gymCentreId);
    }

    public GymCentre getGymCentreById(String centreID) {
        GymCentre gymCentre = gymCentreDAO.getGymCentreByCentreId(centreID);
        return gymCentre;
    }
}
