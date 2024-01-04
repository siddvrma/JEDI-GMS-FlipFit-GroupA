package com.flipkart.DAO;

import com.flipkart.bean.Slot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO implements SlotInterfaceDAO {

    private List<Slot> slotList = new ArrayList<>();

    public SlotDAO() {
    }

    public List<Slot> getSlotList() {
        return new ArrayList<>(slotList);
    }

    public List<Slot> getSlotByCentreId(String gymCentreId) {
        List<Slot> filteredSlots = new ArrayList<>();
        for (Slot slot : slotList) {
            if (slot.getCentreID().equals(gymCentreId)) {
                filteredSlots.add(slot);
            }
        }
        return filteredSlots;
    }

    public void addSlot(Slot slot) {
        // Assuming you have a method to generate a unique Slot ID
        //String slotId = generateUniqueSlotId(slot.getCentreID(), slot.getTime());
        String slotId= slot.getSlotId();

        // Set the generated ID to the Slot object
        slot.setSlotId(slotId);

        // Add the Slot object to the list
        slotList.add(slot);
    }

    public Slot getSlotById(String slotID) {
        for (Slot slot : slotList) {
            if (slot.getSlotId().equals(slotID)) {
                return slot;
            }
        }
        return null;
    }

    public Slot getSlotByIdandCentreId(String slotID, String centreID) {
        for (Slot slot : slotList) {
            if (slot.getSlotId().equals(slotID) && slot.getCentreID().equals(centreID)) {
                return slot;
            }
        }
        return null;
    }

    private String generateUniqueSlotId(String centreID, LocalTime time) {
        return centreID + "-" + time.toString();
    }
}
