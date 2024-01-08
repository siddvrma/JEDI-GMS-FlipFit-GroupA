package com.flipkart.DAO;

import com.flipkart.bean.Slot;

import java.util.List;

/**
 * This interface defines the data access operations related to Slot functionality.
 * It includes methods for retrieving slot lists, adding slots, and retrieving slots
 * based on center and slot IDs.
 */
public interface SlotInterfaceDAO {

    /**
     * Retrieves a list of all available slots.
     *
     * @return List of all available slots
     */
    List<Slot> getSlotList();

    /**
     * Retrieves a list of slots based on the specified gym center ID.
     *
     * @param gymCentreId The ID of the gym center for which slots are retrieved
     * @return List of slots associated with the specified gym center
     */
    List<Slot> getSlotByCentreId(String gymCentreId);

    /**
     * Adds a new slot to the system.
     *
     * @param slot The Slot object representing the details of the new slot
     */
    void addSlot(Slot slot);

    /**
     * Retrieves a specific slot based on the provided slot ID.
     *
     * @param slotID The ID of the slot to be retrieved
     * @return Slot object representing the details of the specified slot
     */
    Slot getSlotById(String slotID);

    /**
     * Retrieves a specific slot based on the provided slot and gym center IDs.
     *
     * @param slotID      The ID of the slot to be retrieved
     * @param gymCentreId The ID of the gym center associated with the slot
     * @return Slot object representing the details of the specified slot and gym center
     */
    Slot getSlotByIdAndCentreId(String slotID, String gymCentreId);
}
