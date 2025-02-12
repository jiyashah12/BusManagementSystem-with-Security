package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    public Optional<Route> findBySourceAndDestination(String source, String Destination);

}
