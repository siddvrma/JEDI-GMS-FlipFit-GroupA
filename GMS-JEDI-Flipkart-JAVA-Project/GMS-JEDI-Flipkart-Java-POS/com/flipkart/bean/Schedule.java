package com.flipkart.bean;

import java.sql.Date;
import java.util.UUID;

/**
 * Represents a schedule in the system.
 */
public class Schedule {

    /**
     * The unique identifier for the schedule.
     */
    private String scheduleID;

    /**
     * The date of the schedule.
     */
    private Date date;

    /**
     * The slot ID associated with the schedule.
     */
    private String slotID;

    /**
     * The availability status of the schedule.
     */
    private int availability;

    /**
     * Constructs a new schedule with a generated unique ID.
     *
     * @param date         The date of the schedule.
     * @param slotID       The slot ID associated with the schedule.
     * @param availability The availability status of the schedule.
     */
    public Schedule(Date date, String slotID, int availability) {
        this.scheduleID = String.valueOf(UUID.randomUUID());
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    /**
     * Constructs a new schedule with the provided schedule ID.
     *
     * @param scheduleID   The unique identifier for the schedule.
     * @param date         The date of the schedule.
     * @param slotID       The slot ID associated with the schedule.
     * @param availability The availability status of the schedule.
     */
    public Schedule(String scheduleID, Date date, String slotID, int availability) {
        this.scheduleID = scheduleID;
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    /**
     * Gets the unique identifier for the schedule.
     *
     * @return The schedule ID.
     */
    public String getScheduleID() {
        return scheduleID;
    }

    /**
     * Sets the unique identifier for the schedule.
     *
     * @param scheduleID The schedule ID.
     */
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * Gets the date of the schedule.
     *
     * @return The date of the schedule.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the schedule.
     *
     * @param date The date of the schedule.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the slot ID associated with the schedule.
     *
     * @return The slot ID.
     */
    public String getSlotID() {
        return slotID;
    }

    /**
     * Sets the slot ID associated with the schedule.
     *
     * @param slotID The slot ID.
     */
    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    /**
     * Gets the availability status of the schedule.
     *
     * @return The availability status.
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the schedule.
     *
     * @param availability The availability status.
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
