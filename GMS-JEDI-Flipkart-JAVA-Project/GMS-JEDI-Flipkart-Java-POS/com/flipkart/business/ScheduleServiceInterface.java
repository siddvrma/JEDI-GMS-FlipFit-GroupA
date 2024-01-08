package com.flipkart.business;

import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

/**
 * This interface defines the service operations related to Schedule functionality.
 * It includes methods for creating schedules, retrieving schedules by date and slot ID,
 * modifying schedules, checking availability, getting all available slots by date,
 * and getting or creating a schedule for a specific slot and date.
 */
public interface ScheduleServiceInterface {

    /**
     * Creates a new schedule for a specific date and slot.
     *
     * @param date   The date for the schedule
     * @param slotId The ID of the slot for the schedule
     * @return Schedule object representing the created schedule
     */
    Schedule createSchedule(Date date, String slotId);

    /**
     * Retrieves a schedule based on the provided slot ID and date.
     *
     * @param slotId The ID of the slot
     * @param date   The date for which the schedule is retrieved
     * @return Schedule object representing the details of the schedule
     */
    Schedule getScheduleByDateAndSlotId(String slotId, Date date);

    /**
     * Modifies a schedule based on the provided schedule ID and action.
     *
     * @param scheduleId The ID of the schedule to be modified
     * @param action     The action to be performed on the schedule
     * @return True if the modification is successful, false otherwise
     */
    boolean modifySchedule(String scheduleId, int action);

    /**
     * Checks the availability of schedules for a specific gym center and date.
     *
     * @param centreID The ID of the gym center
     * @param date     The date for which availability is checked
     * @return List of schedules available for the specified gym center and date
     */
    List<Schedule> checkAvailability(String centreID, Date date);

    /**
     * Retrieves all available slots for a specific gym center and date.
     *
     * @param centreID The ID of the gym center
     * @param date     The date for which available slots are retrieved
     * @return List of available slots for the specified gym center and date
     */
    List<Slot> getAllAvailableSlotsByDate(String centreID, Date date);

    /**
     * Retrieves or creates a schedule for a specific slot and date.
     *
     * @param slotId The ID of the slot
     * @param date   The date for which the schedule is retrieved or created
     * @return Schedule object representing the details of the schedule
     */
    Schedule getOrCreateSchedule(String slotId, Date date);
}
