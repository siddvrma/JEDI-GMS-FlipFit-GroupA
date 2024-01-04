package com.flipkart.DAO;

import com.flipkart.bean.Schedule;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO implements ScheduleInterfaceDAO {

    private List<Schedule> scheduleList = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        // Assuming you have a method to generate a unique Schedule ID
        //String scheduleId = generateUniqueScheduleId(schedule.getDate(), schedule.getSlotID());
        String scheduleId = schedule.getScheduleID();
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
        return true;
    }

    private String generateUniqueScheduleId(Date date, String slotId) {
        // Implement logic to generate a unique Schedule ID based on your requirements
        return date.toString() + slotId;
    }
}
