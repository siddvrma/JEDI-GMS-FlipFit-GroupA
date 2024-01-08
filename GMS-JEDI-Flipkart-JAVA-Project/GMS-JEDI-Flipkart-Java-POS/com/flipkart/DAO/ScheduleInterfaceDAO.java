package com.flipkart.DAO;

import com.flipkart.bean.Schedule;

import java.sql.Date;
import java.util.List;

/**
 * This interface defines the data access operations related to Schedule functionality.
 * It includes methods for adding schedules and retrieving schedules by date.
 */
public interface ScheduleInterfaceDAO {

    /**
     * Adds a new schedule to the system.
     *
     * @param schedule The Schedule object representing the details of the new schedule
     */
    void addSchedule(Schedule schedule);

    /**
     * Retrieves a list of schedules based on the specified date.
     *
     * @param date The date for which schedules are retrieved
     * @return List of schedules on the specified date
     */
    List<Schedule> getAllScheduleByDate(Date date);
}
