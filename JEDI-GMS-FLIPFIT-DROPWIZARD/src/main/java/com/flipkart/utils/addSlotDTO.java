package com.flipkart.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class addSlotDTO {
    private String slotId;
    private String centreID;
    private LocalTime time;

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

    public void setTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.parse(time, formatter);
        this.time = localTime;
    }
}
