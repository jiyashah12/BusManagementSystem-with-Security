package com.example.BusManagementSystem.entities;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles_info")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String role_name;

//    @ManyToMany(mappedBy = "roles")
//    private List<Customer> customers;

    @OneToMany(mappedBy = "role")
    private List<Customer> customers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}






