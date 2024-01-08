package com.flipkart.DAO;

import com.flipkart.bean.Slot;
import com.flipkart.constant.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO implements SlotInterfaceDAO {
    public SlotDAO(){
    }

    public List<Slot> getSlotList() {
        List<Slot> slotList = new ArrayList<>();
        try{
            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_ALL_SLOTS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new Slot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    public List<Slot> getSlotByCentreId(String gymCentreId){
        List<Slot> slotList = new ArrayList<>();
        try{
            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_CENTRE);
            ps.setString(1,gymCentreId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new Slot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    public void addSlot(Slot slot){
        try{
            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_SLOT);
            ps.setString(1, slot.getSlotId());
            ps.setString(2, slot.getCentreID());
            ps.setTime(3, java.sql.Time.valueOf(slot.getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Slot getSlotById(String slotID) {
        Slot slot = null;
        try{
            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID);
            ps.setString(1,slotID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new Slot(slotID, centreId, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }

    public Slot getSlotByIdAndCentreId(String slotID,String centreID) {
        Slot slot = null;
        try{
            Connection conn = DBConnection.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID_AND_CENTRE_ID);
            ps.setString(1,slotID);
            ps.setString(2,centreID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new Slot(slotID, centreID, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }
}
