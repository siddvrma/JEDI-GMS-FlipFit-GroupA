package com.flipkart.DAO;

import com.flipkart.DAO.ScheduleInterfaceDAO;
import com.flipkart.bean.Schedule;
import com.flipkart.connection.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO implements ScheduleInterfaceDAO {

    private static List<Schedule> scheduleList = loadSchedulesFromDatabase();

    public void addSchedule(Schedule schedule) {
        // Assuming you have a method to generate a unique Schedule ID
        String scheduleId = insertScheduleIntoDatabase(schedule);
        // Set the generated ID to the Schedule object
        schedule.setScheduleID(scheduleId);

        // Add the Schedule object to the list
        scheduleList.add(schedule);
    }

    public Schedule getSchedule(String scheduleId) {
        for (Schedule schedule : scheduleList) {
            if (schedule.getScheduleID().equals(scheduleId)) {
                return schedule;
            }
        }
        return null;
    }

    public List<Schedule> getAllScheduleByDate(Date date) {
        List<Schedule> response = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            if (schedule.getDate().equals(date)) {
                response.add(schedule);
            }
        }
        return response;
    }

    public boolean modifySchedule(String scheduleId, int action) {
        // 1 for increasing, -1 for decreasing
        Schedule schedule = getSchedule(scheduleId);
        if (schedule == null) {
            return false;
        }
        int availability = schedule.getAvailability();
        if (availability < 1 && action == -1) {
            return false;
        }
        // Update the availability
        schedule.setAvailability(availability + action);

        // Update the modified schedule in the database
        updateScheduleInDatabase(schedule);

        return true;
    }

    private String insertScheduleIntoDatabase(Schedule schedule) {
        String scheduleId = generateUniqueScheduleId(schedule.getDate(), schedule.getSlotID());

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Schedule(scheduleID, date, slotID, availability) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, scheduleId);
            preparedStatement.setDate(2, schedule.getDate());
            preparedStatement.setString(3, schedule.getSlotID());
            preparedStatement.setInt(4, schedule.getAvailability());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe throw a custom exception
        }

        return scheduleId;
    }

    private static List<Schedule> loadSchedulesFromDatabase() {
        List<Schedule> loadedSchedules = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Schedule");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String scheduleId = resultSet.getString("scheduleID");
                Date date = resultSet.getDate("date");
                String slotId = resultSet.getString("slotID");
                int availability = resultSet.getInt("availability");

                Schedule schedule = new Schedule(scheduleId, date, slotId, availability);
                loadedSchedules.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe throw a custom exception
        }

        return loadedSchedules;
    }

    private void updateScheduleInDatabase(Schedule schedule) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Schedule SET availability = ? WHERE scheduleID = ?")) {

            preparedStatement.setInt(1, schedule.getAvailability());
            preparedStatement.setString(2, schedule.getScheduleID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe throw a custom exception
        }
    }

    private String generateUniqueScheduleId(Date date, String slotId) {
        // Implement logic to generate a unique Schedule ID based on your requirements
        return date.toString() + slotId;
    }
}
