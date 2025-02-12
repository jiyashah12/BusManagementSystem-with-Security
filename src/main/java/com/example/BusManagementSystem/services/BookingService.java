package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Bus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public void deleteBooking(long booking_id);

    public List<Bus> getBusByPreference(String source, String destination);

    public ResponseEntity<String> doBooking(long cust_id, LocalDate date, long bus_id, String seats);


}



