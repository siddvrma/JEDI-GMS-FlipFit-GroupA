package com.flipkart.DAO;

import com.flipkart.bean.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleInterfaceDAO {
    void addSchedule(Schedule schedule);
    List<Schedule> getAllScheduleByDate(Date date);
}
