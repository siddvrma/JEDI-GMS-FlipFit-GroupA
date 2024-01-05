package com.flipkart.DAOLocal;

import com.flipkart.bean.Slot;

import java.util.List;

public interface SlotInterfaceDAO {
    List<Slot> getSlotList();
    List<Slot> getSlotByCentreId(String gymCentreId);
    void addSlot(Slot slot);
    Slot getSlotById(String slotID);
    Slot getSlotByIdandCentreId(String slotID,String gymCentreId);
}
