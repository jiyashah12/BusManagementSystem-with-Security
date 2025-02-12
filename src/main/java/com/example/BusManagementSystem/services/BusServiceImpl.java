package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.*;
import com.example.BusManagementSystem.exceptions.ResourceNotFoundException;
import com.example.BusManagementSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addBus(long route_id, long schedule_id, Bus bus) {
        Optional<Route> optionalRoute = routeRepository.findById(route_id);
        if (!optionalRoute.isPresent()) {
            throw new ResourceNotFoundException("Route not found.");
        }
        Route route = optionalRoute.get();

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(schedule_id);
        if (!optionalSchedule.isPresent()) {
            throw new ResourceNotFoundException("Schedule not found.");
        }
        Schedule schedule = optionalSchedule.get();

        bus.setRoute(route);
        bus.setSchedule(schedule);

        busRepository.save(bus);
    }


    @Override
    public void removeBus(long id) {
        busRepository.deleteById(id);
    }


    @Override
    public List<Bus> getAllDetails() {
        return busRepository.findAll();
    }


    @Override
    public Bus updateBus(Bus bus) {
        return busRepository.save(bus);
    }


    @Override
    public Bus getBusById(long bus_id) {
        Optional<Bus> busOptional = busRepository.findById(bus_id);
        return busOptional.get();
    }

}


