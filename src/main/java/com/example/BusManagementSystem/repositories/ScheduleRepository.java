package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
