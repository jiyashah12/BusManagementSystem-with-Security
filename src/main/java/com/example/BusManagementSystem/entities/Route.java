package com.example.BusManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "route_info")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @NotBlank(message = "Source can't be blank.")
    @Column
    private String source;

    @NotBlank(message = "Destination can't be blank.")
    @Column
    private String destination;

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    private List<Bus> buses;

    @NotBlank(message = "Distance can't be blank.")
    @Column
    private String distance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
