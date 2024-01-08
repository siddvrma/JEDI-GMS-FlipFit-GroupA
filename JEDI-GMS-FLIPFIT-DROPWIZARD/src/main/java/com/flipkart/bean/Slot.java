package com.flipkart.bean;

import java.time.LocalTime;

public class Slot {
    private String slotId;
    private String centreID;
    private LocalTime time;

    public Slot(String slotId, String centreID, LocalTime time) {
        this.slotId = slotId;
        this.centreID = centreID;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "slotId='" + slotId + '\'' +
                ", time=" + time +
                '}';
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getCentreID() {
        return centreID;
    }

    public void setCentreID(String centreID) {
        this.centreID = centreID;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
