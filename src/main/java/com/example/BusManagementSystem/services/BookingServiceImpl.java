package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.*;
import com.example.BusManagementSystem.exceptions.ResourceNotFoundException;
import com.example.BusManagementSystem.repositories.BookingRepository;
import com.example.BusManagementSystem.repositories.BusRepository;
import com.example.BusManagementSystem.repositories.CustomerRepository;
import com.example.BusManagementSystem.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public void deleteBooking(long booking_id) {
        if (!bookingRepository.existsById(booking_id)) {
            throw new ResourceNotFoundException("Booking not found.");
        }

        bookingRepository.deleteById(booking_id);
    }


    @Override
    public List<Bus> getBusByPreference(String source, String destination) {

        Optional<Route> optionalValue = routeRepository.findBySourceAndDestination(source, destination);
        long id = optionalValue.get().getId();

        List<Bus> busList = busRepository.findByRouteId(id);
        return busList;
    }


    @Override
    public ResponseEntity<String> doBooking(long cust_id, LocalDate date, long bus_id, String seats) {
        if (Integer.parseInt(seats) < 1) {
            return ResponseEntity.badRequest().body("Minimum 1 seat required to book.");
        }

        Optional<Bus> optionalBus = busRepository.findById(bus_id);
        Bus bus = optionalBus.get();

        if (!optionalBus.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found. ");
        } else {
            int bus_capacity = Integer.parseInt(optionalBus.get().getCapacity());
            int seatsBookedByUser = Integer.parseInt(seats);

            long totalSeatsBooked = bookingRepository.findTotalSeatsBookedInBus(bus_id, date).orElse(0L);

            if (seatsBookedByUser + totalSeatsBooked <= bus_capacity) {

                Booking booking = new Booking();
                booking.setBus(bus);
                Optional<Customer> optionalCustomer = customerRepository.findById(cust_id);
                if (optionalCustomer.isPresent()) {
                    booking.setCustomer(optionalCustomer.get());
                }
                booking.setDate(date);
                booking.setSeats_booked(seatsBookedByUser);
//                booking.setStatus(BookingStatus.valueOf("CONFIRMED"));
                booking.setStatus(BookingStatus.CONFIRMED);
                bookingRepository.save(booking);
                return ResponseEntity.ok("Booking successful.");
            } else {
                long availableSeats = bus_capacity - totalSeatsBooked;
                if (availableSeats > 0) {
                    return ResponseEntity.badRequest().body("Only " + availableSeats + " seats are available.");
                }
                return ResponseEntity.badRequest().body("Bus is fully booked.");
            }

        }

    }
}


