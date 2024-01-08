package com.flipkart.business;

import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

public interface ScheduleServiceInterface {
    Schedule createSchedule(Date date, String slotId);
    Schedule getScheduleByDateAndSlotId(String SlotId, Date date);
    boolean modifySchedule(String scheduleId,int action);
    List<Schedule> checkAvailability(String centreID, Date date);
    List<Slot> getAllAvailableSlotsByDate(String centreID, Date date);
    Schedule getOrCreateSchedule(String slotId, Date date);
}
