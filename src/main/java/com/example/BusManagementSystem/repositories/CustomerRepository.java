package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByEmail(String email);

    Customer findByUsername(String username);
}
