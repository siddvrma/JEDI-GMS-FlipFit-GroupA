package com.flipkart.business;

import com.flipkart.DAO.SlotDAO;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.utils.addSlotDTO;

import java.util.List;

public class SlotService implements SlotServiceInterface {
    private static SlotDAO slotDAO = new SlotDAO();
    private GymCentreServiceInterface gymCentreService = new GymCentreService();
    public List<Slot> getAllSlotsByCentre(String centreID) {
        return slotDAO.getSlotByCentreId(centreID);
    }

    public Slot getSlotByID(String slotID){
        return slotDAO.getSlotById(slotID);
    }

    public Slot getSlotByIDandCentreId(String slotID, String centreId){
        return slotDAO.getSlotByIdandCentreId(slotID,centreId);
    }

    public List<Slot> getSlotList(){
        return slotDAO.getSlotList();
    }

public void addSlotListForGym(String gymCentreId, List<addSlotDTO> slotList) {
    System.out.println("Adding all slots to gym: " + gymCentreId);

    slotList.stream()
            .filter(slot -> !gymCentreService.isValdidCentre(gymCentreId))
            .forEach(slot -> slotDAO.addSlot(slot.getSlotId(), slot.getCentreID(), slot.getTime()));
}


    public boolean isSlotValid(String slotID,String centreId){
        return getSlotByIDandCentreId(slotID,centreId) != null;
    }
}
