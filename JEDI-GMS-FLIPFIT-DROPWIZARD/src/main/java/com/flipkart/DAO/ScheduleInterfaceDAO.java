package com.flipkart.DAO;

import com.flipkart.bean.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleInterfaceDAO {
    public void addSchedule(Schedule schedule);
    public List<Schedule> getAllScheduleByDate(Date date);

}
