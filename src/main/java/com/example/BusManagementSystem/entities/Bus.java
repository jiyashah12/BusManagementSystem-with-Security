package com.example.BusManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "bus_info")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long busId;

    @NotBlank(message = "Bus Number can't be blank.")
    @Column
    private String bus_number;

    @Enumerated(EnumType.STRING)
    private BusType type;

    @NotBlank(message = "Capacity of bus can't be min. Please add minimum 1 seat.")
    @Column
    private String capacity;

    @NotBlank(message = "Operator can't be blank.")
    @Column
    private String operator;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @JsonIgnore
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public BusType getType() {
        return type;
    }

    public void setType(BusType type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}




