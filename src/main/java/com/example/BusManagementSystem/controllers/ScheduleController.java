package com.example.BusManagementSystem.controllers;

import com.example.BusManagementSystem.entities.Schedule;
import com.example.BusManagementSystem.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/addschedule")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule) {
            scheduleService.addSchedule(schedule);
            return ResponseEntity.ok("Schedule added successfully.");
    }

    @GetMapping("/getallschedules")
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return schedules;
    }


    @GetMapping("/getschedulebyid/{schedule_id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable long schedule_id) {
        Schedule schedule = scheduleService.getScheduleById(schedule_id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/updateschedule/{schedule_id}")
    public ResponseEntity<String> updateSchedule(@PathVariable long schedule_id, @RequestBody Schedule schedule) {
        try {
            Schedule existingSchedule = scheduleService.getScheduleById(schedule_id);

            if (existingSchedule != null) {
                schedule.setId(existingSchedule.getId());
                scheduleService.updateSchedule(schedule);
                return ResponseEntity.ok("Schedule updated successfully.");
            }

            return ResponseEntity.status(404).body("Schedule not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Schedule not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error updating Schedule.");
        }
    }

    @DeleteMapping("/deleteschedulebyid/{schedule_id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable long schedule_id) {
        try {
            Schedule schedule = scheduleService.getScheduleById(schedule_id);
            if (schedule != null) {
                scheduleService.deleteScheduleById(schedule_id);
                return ResponseEntity.ok("Schedule deleted successfully.");
            }
            return ResponseEntity.status(404).body("Schedule not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Schedule not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error deleting schedule.");
        }
    }
}
