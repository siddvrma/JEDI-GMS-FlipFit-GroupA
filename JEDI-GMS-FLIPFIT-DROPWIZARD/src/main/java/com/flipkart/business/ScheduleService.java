package com.flipkart.business;

import com.flipkart.DAO.ScheduleDAO;
import com.flipkart.bean.Schedule;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService implements ScheduleServiceInterface {

    private GymCentreService gymCentreService = new GymCentreService();
    private SlotService slotService = new SlotService();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public Schedule createSchedule(Date date, String slotId){
        String centreID = slotService.getSlotByID(slotId).getCentreID();
        int availability = gymCentreService.getGymCentreById(centreID).getCapacity();
        Schedule schedule = new Schedule( date, slotId, availability);
        scheduleDAO.addSchedule(schedule);

        return schedule;
    }

    public Schedule getScheduleByDateAndSlotId(String SlotId, Date date) {
        List<Schedule> scheduleList = scheduleDAO.getAllScheduleByDate(date);
        return scheduleList.stream()
                .filter(schedule -> schedule.getSlotID().equals(SlotId))
                .findFirst()
                .orElse(null);
    }


    public boolean modifySchedule(String scheduleId,int action){
        // increment or decrement availability based on action
        // 1 for increasing availability, -1 for decreasing
        return scheduleDAO.modifySchedule(scheduleId, action);
    }

    public List<Schedule> checkAvailability(String centreID, Date date) {
        List<Slot> allSlotsForGym = slotService.getAllSlotsByCentre(centreID);
        return allSlotsForGym.stream()
                .map(slot -> getOrCreateSchedule(slot.getSlotId(), date))
                .filter(schedule -> schedule.getAvailability() > 0)
                .collect(Collectors.toList());
    }


    public List<Slot> getAllAvailableSlotsByDate(String centreID, Date date) {
        List<Slot> allSlotsOfThatCentre = slotService.getAllSlotsByCentre(centreID);
        return allSlotsOfThatCentre.stream()
                .filter(slot -> scheduleDAO.getAllScheduleByDate(date).stream()
                        .anyMatch(schedule ->
                                slotService.getSlotByID(schedule.getSlotID()).getCentreID().equals(centreID) &&
                                        schedule.getAvailability() > 0
                        )
                )
                .collect(Collectors.toList());
    }


    public Schedule getSchedule(String scheduleID){
        return scheduleDAO.getSchedule(scheduleID);
    }

    public Schedule getOrCreateSchedule(String slotId, Date date) {
        Schedule schedule = getScheduleByDateAndSlotId(slotId, date);
        if( schedule == null ){
            return createSchedule(date,slotId);
        }
        return schedule;

    }
}
