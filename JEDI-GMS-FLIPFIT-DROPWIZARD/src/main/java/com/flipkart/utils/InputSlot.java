package com.flipkart.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InputSlot {
    String userID;
    Date date;
    String slotId;
    String centreId;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        //Date date = java.sql.Date.valueOf(dateStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        java.util.Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Error in parsing date");
        }
        this.date =new java.sql.Date(date.getTime());
        //this.date = date;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getCentreId() {
        return centreId;
    }

    public void setCentreId(String centreId) {
        this.centreId = centreId;
    }
}
