package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Schedule;
import java.util.List;

public interface ScheduleService {

    public void addSchedule(Schedule schedule);

    public List<Schedule> getAllSchedules();

    public Schedule getScheduleById(long schedule_id);

    public Schedule updateSchedule(Schedule schedule_id);

    public void deleteScheduleById(long schedule_id);

}
