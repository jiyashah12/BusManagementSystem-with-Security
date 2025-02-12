package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsById(Long bookingId);


    @Query("SELECT SUM(b.seats_booked) FROM Booking b " +
            "WHERE b.bus.id = :busId " +
            "AND b.date = :date")
    public Optional<Long> findTotalSeatsBookedInBus(@Param("busId") Long busId,
                                                    @Param("date") LocalDate date);


    public void deleteAllById(Long cust_id);

}

