package com.example.BusManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "schedule_info")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column
    private String arrivalTime;

    @NotBlank
    @Column
    private String departureTime;

    @JsonIgnore
    @OneToMany(mappedBy = "schedule")
    private List<Bus> buses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
}

