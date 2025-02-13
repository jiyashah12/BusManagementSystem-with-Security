package com.example.BusManagementSystem.controllers;

import com.example.BusManagementSystem.entities.Bus;
import com.example.BusManagementSystem.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/admin/addbus/{route_id}/{schedule_id}")
    public ResponseEntity<String> addBus(@PathVariable long route_id, @PathVariable long schedule_id, @RequestBody Bus bus) {
        try {
            busService.addBus(route_id, schedule_id, bus);
            return ResponseEntity.ok("Bus added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error adding bus.");
        }
    }


    @DeleteMapping("/admin/removebusbyid/{id}")
    public ResponseEntity<String> removeBus(@PathVariable long id) {
        try {
            busService.removeBus(id);
            return ResponseEntity.ok("Bus deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error deleting bus.");
        }
    }


    @GetMapping("/getalldetails")
    public List<Bus> getAllDetails() {
        System.out.println("Executed....");
        List<Bus> buses = busService.getAllDetails();
        return buses;
    }


    @GetMapping("/getbusbyid/{bus_id}")
    public ResponseEntity<Bus> getBusById(@PathVariable long bus_id) {
        Bus bus = busService.getBusById(bus_id);
        return ResponseEntity.ok(bus);
    }


    @PutMapping("/admin/updatebus/{bus_id}")
    public ResponseEntity<String> updateBus(@PathVariable long bus_id, @RequestBody Bus bus) {
        try {
            Bus existingBus = busService.getBusById(bus_id);

            if (existingBus != null) {
                bus.setBusId(existingBus.getBusId());
                busService.updateBus(bus);
                return ResponseEntity.ok("Bus updated successfully.");
            }

            return ResponseEntity.status(404).body("Bus not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Bus not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error updating bus.");
        }
    }

}



