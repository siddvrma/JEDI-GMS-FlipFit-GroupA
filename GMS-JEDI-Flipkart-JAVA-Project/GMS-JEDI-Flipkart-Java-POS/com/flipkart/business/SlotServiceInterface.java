package com.flipkart.business;

import com.flipkart.bean.Slot;

import java.util.List;

/**
 * This interface defines the service operations related to Slot functionality.
 * It includes methods for retrieving slots by gym center, getting a slot by ID,
 * getting a list of all slots, adding slots for a gym center, and validating slots.
 */
public interface SlotServiceInterface {

    /**
     * Retrieves all slots associated with a specific gym center.
     *
     * @param centreID The ID of the gym center
     * @return List of slots associated with the specified gym center
     */
    List<Slot> getAllSlotsByCentre(String centreID);

    /**
     * Retrieves details of a specific slot based on the provided slot ID.
     *
     * @param slotID The ID of the slot
     * @return Slot object representing the details of the specified slot
     */
    Slot getSlotByID(String slotID);

    /**
     * Retrieves a list of all available slots.
     *
     * @return List of all available slots
     */
    List<Slot> getSlotList();

    /**
     * Adds slots for a specific gym center.
     *
     * @param gymCentreId The ID of the gym center for which slots are added
     * @param slotList    List of slots to be added for the gym center
     */
    void addSlotsForGym(String gymCentreId, List<Slot> slotList);

    /**
     * Checks if a slot is valid for a specific gym center.
     *
     * @param slotID   The ID of the slot
     * @param centreId The ID of the gym center
     * @return True if the slot is valid for the gym center, false otherwise
     */
    boolean isSlotValid(String slotID, String centreId);
}
