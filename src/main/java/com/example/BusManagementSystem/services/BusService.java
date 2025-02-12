package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Bus;
import java.util.List;

public interface BusService {

    public void addBus(long route_id, long schedule_id, Bus bus);

    public void removeBus(long id);

    public Bus getBusById(long bus_id);

    public List<Bus> getAllDetails();

    public Bus updateBus(Bus bus);

}





