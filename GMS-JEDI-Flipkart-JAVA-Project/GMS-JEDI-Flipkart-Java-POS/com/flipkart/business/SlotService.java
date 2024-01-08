package com.flipkart.business;

import com.flipkart.DAO.SlotDAO;
import com.flipkart.bean.Slot;

import java.util.List;

public class SlotService implements SlotServiceInterface {
    private static SlotDAO slotDAO = new SlotDAO();
    public List<Slot> getAllSlotsByCentre(String centreID) {
        return slotDAO.getSlotByCentreId(centreID);
    }

    public Slot getSlotByID(String slotID){
        return slotDAO.getSlotById(slotID);
    }

    public Slot getSlotByIDAndCentreId(String slotID, String centreId){
        return slotDAO.getSlotByIdAndCentreId(slotID,centreId);
    }

    public List<Slot> getSlotList(){
        return slotDAO.getSlotList();
    }

    public void addSlotsForGym(String gymCentreId, List<Slot> slotList){
        System.out.println("Adding all slots to gym: " + gymCentreId);
        for(Slot slot : slotList) {
            slot.setCentreID(gymCentreId);
            slotDAO.addSlot(slot);
        }
    }

    public boolean isSlotValid(String slotID,String centreId){
        return getSlotByIDAndCentreId(slotID,centreId) != null;
    }
}
