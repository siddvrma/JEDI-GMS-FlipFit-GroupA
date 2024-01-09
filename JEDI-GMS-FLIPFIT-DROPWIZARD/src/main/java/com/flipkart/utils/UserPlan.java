package com.flipkart.utils;

import java.sql.Date;
import java.time.LocalTime;

public class UserPlan {
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

    private String slotId;
    private String centreID;
    private LocalTime time;
    private String scheduleID;
    private Date date;

    public UserPlan(String slotId,String centreID,LocalTime time,String scheduleID,Date date){
        this.time = time;
        this.slotId = slotId;
        this.date = date;
        this.scheduleID = scheduleID;
        this.centreID  = centreID;
    }

}
