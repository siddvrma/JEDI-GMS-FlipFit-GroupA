package com.flipkart.bean;

import java.sql.Date;
import java.util.UUID;

public class Schedule {

    private String scheduleID;
    private Date date;
    private String slotID;
    private int availability;

    public Schedule( Date date, String slotID, int availability) {
        this.scheduleID = String.valueOf(UUID.randomUUID());
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    public Schedule(String scheduleID, Date date, String slotID, int availability) {
        this.scheduleID = scheduleID;
        this.date = date;
        this.slotID = slotID;
        this.availability = availability;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSlotID() {
        return slotID;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }


}
