package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    public List<Bus> findByRouteId(long id);

}



