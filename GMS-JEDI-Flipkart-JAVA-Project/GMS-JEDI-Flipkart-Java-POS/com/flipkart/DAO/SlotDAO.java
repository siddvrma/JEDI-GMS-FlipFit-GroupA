package com.flipkart.DAO;

import com.flipkart.bean.Slot;
import com.flipkart.connection.DBConnection;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO implements SlotInterfaceDAO {

    private static final String INSERT_SLOT_SQL = "INSERT INTO slots (slot_id, centre_id, start_time) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SLOTS_SQL = "SELECT * FROM slots";
    private static final String SELECT_SLOTS_BY_CENTRE_SQL = "SELECT * FROM slots WHERE centre_id = ?";
    private static final String SELECT_SLOT_BY_ID_SQL = "SELECT * FROM slots WHERE slot_id = ?";
    private static final String SELECT_SLOT_BY_ID_AND_CENTRE_SQL = "SELECT * FROM slots WHERE slot_id = ? AND centre_id = ?";

    public SlotDAO() {
    }

    public List<Slot> getSlotList() {
        List<Slot> slots = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SLOTS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Slot slot = extractSlotFromResultSet(resultSet);
                slots.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return slots;
    }

    public List<Slot> getSlotByCentreId(String gymCentreId) {
        List<Slot> filteredSlots = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SLOTS_BY_CENTRE_SQL)) {

            preparedStatement.setString(1, gymCentreId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Slot slot = extractSlotFromResultSet(resultSet);
                    filteredSlots.add(slot);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredSlots;
    }

    public void addSlot(Slot slot) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SLOT_SQL)) {

            preparedStatement.setString(1, slot.getSlotId());
            preparedStatement.setString(2, slot.getCentreID());
            preparedStatement.setTime(3, Time.valueOf(slot.getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Slot getSlotById(String slotID) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SLOT_BY_ID_SQL)) {

            preparedStatement.setString(1, slotID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractSlotFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Slot getSlotByIdandCentreId(String slotID, String centreID) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SLOT_BY_ID_AND_CENTRE_SQL)) {

            preparedStatement.setString(1, slotID);
            preparedStatement.setString(2, centreID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractSlotFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Slot extractSlotFromResultSet(ResultSet resultSet) throws SQLException {
        String slotId = resultSet.getString("slot_id");
        String centreId = resultSet.getString("centre_id");
        LocalTime time = resultSet.getTime("start_time").toLocalTime();

        return new Slot(slotId, centreId, time);
    }
}
