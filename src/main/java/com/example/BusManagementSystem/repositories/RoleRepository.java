package com.example.BusManagementSystem.repositories;

import com.example.BusManagementSystem.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.directory.InvalidAttributeIdentifierException;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long > {
}


