package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Schedule;
import com.example.BusManagementSystem.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void addSchedule(Schedule schedule) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime arrivalTime = LocalTime.parse(schedule.getArrivalTime(), formatter);
            LocalTime departureTime = LocalTime.parse(schedule.getDepartureTime(), formatter);

            if (departureTime.isAfter(arrivalTime)) {
                scheduleRepository.save(schedule);
            } else {
                throw new IllegalArgumentException("Departure time must be greater than arrival time.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please provide time in HH:mm format.", e);
        }
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(long schedule_id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule_id);
        return scheduleOptional.get();
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteScheduleById(long schedule_id) {
        scheduleRepository.deleteById(schedule_id);
    }
}

