package com.example.BusManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "booking_info")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @FutureOrPresent(message = "You can't access Booking services for past days.")
    @Column
    private LocalDate date;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Min(value = 1, message = "Minimum number of seats to be booked is 1.")
    @Column
    private int seats_booked;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getSeats_booked() {
        return seats_booked;
    }

    public void setSeats_booked(int seats_booked) {
        this.seats_booked = seats_booked;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}




