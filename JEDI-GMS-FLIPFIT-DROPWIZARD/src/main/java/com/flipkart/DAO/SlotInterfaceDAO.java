package com.flipkart.DAO;

import com.flipkart.bean.Slot;

import java.time.LocalTime;
import java.util.List;

public interface SlotInterfaceDAO {
    public List<Slot> getSlotList();
    public List<Slot> getSlotByCentreId(String gymCentreId);
    void addSlot(String slotID, String centreID, LocalTime time);
    public Slot getSlotById(String slotID);
    public Slot getSlotByIdandCentreId(String slotID, String gymCentreId);
}
