package com.flipkart.bean;

import java.time.LocalTime;

/**
 * Represents a time slot in the system.
 */
public class Slot {

    /**
     * The unique identifier for the slot.
     */
    private String slotId;

    /**
     * The unique identifier for the gym center associated with the slot.
     */
    private String centreID;

    /**
     * The time of the slot.
     */
    private LocalTime time;

    /**
     * Constructs a new slot with the provided slot ID, gym center ID, and time.
     *
     * @param slotId   The unique identifier for the slot.
     * @param centreID The unique identifier for the gym center associated with the slot.
     * @param time     The time of the slot.
     */
    public Slot(String slotId, String centreID, LocalTime time) {
        this.slotId = slotId;
        this.centreID = centreID;
        this.time = time;
    }

    /**
     * Gets the unique identifier for the slot.
     *
     * @return The slot ID.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique identifier for the slot.
     *
     * @param slotId The slot ID.
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Gets the unique identifier for the gym center associated with the slot.
     *
     * @return The gym center ID.
     */
    public String getCentreID() {
        return centreID;
    }

    /**
     * Sets the unique identifier for the gym center associated with the slot.
     *
     * @param centreID The gym center ID.
     */
    public void setCentreID(String centreID) {
        this.centreID = centreID;
    }

    /**
     * Gets the time of the slot.
     *
     * @return The time of the slot.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the slot.
     *
     * @param time The time of the slot.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns a string representation of the slot.
     *
     * @return A string representation of the slot.
     */
    @Override
    public String toString() {
        return "Slot{" +
                "slotId='" + slotId + '\'' +
                ", time=" + time +
                '}';
    }
}
