package com.flipkart.business;

import com.flipkart.bean.Slot;
import com.flipkart.utils.addSlotDTO;

import java.util.List;

public interface SlotServiceInterface {
    List<Slot> getAllSlotsByCentre(String centreID);
    Slot getSlotByID(String slotID);
    List<Slot> getSlotList();
//    void addSlotListForGym(String gymCentreId, List<Slot> slotList);
    void addSlotListForGym(String gymCentreId, List<addSlotDTO> slotList);
    boolean isSlotValid(String slotID,String centreId);
}
