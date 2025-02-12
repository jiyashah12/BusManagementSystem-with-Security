package com.example.BusManagementSystem.controllers;

import com.example.BusManagementSystem.entities.Customer;
import com.example.BusManagementSystem.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addcustomer")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.ok("Customer added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error adding customer.");
        }
    }


    @GetMapping("/viewAllCustomers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }


    @GetMapping("/getcustomerbyid/{cust_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long cust_id) {
        Customer customer = customerService.getCustomerById(cust_id);
        return ResponseEntity.ok(customer);
    }


    @PutMapping("/updatecustomer/{cust_id}")
    public ResponseEntity<String> updateCustomer(@Valid @PathVariable long cust_id, @RequestBody Customer customer) {
        try {
            Customer cust1 = customerService.getCustomerById(cust_id);

            if (cust1 != null) {
                customer.setId(cust1.getId());
                customerService.updateCustomer(customer);
                return ResponseEntity.ok("Customer updated successfully.");
            }

            return ResponseEntity.status(404).body("Customer not found.");

        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Customer not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error updating customer.");
        }
    }


    @DeleteMapping("/deletecustomerbyid/{cust_id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable long cust_id) {
        try {
            Customer customer = customerService.getCustomerById(cust_id);
            if (customer != null) {
                customerService.deleteCustomerById(cust_id);
                return ResponseEntity.ok("Customer deleted successfully.");
            }
            return ResponseEntity.status(404).body("Customer not found.");
        } catch (NoSuchElementException n) {
            return ResponseEntity.status(404).body("Customer not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error deleting customer.");
        }
    }


    @DeleteMapping("/deleteallcustomers")
    public ResponseEntity<String> deleteAllCustomers() {
        try {
            customerService.deleteAllCustomers();
            return ResponseEntity.ok("All customers deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error deleting customers.");
        }
    }


    @GetMapping("/getcustomersbyemail/{email}")
    public Customer getCustomersByEmail(@PathVariable String email) {
        Customer customers = customerService.getCustomerByEmail(email);
        return customers;
    }


    @PostMapping("/login")
    public String login(@RequestBody Customer customer){
//        System.out.println(user);
//        return "Success";
        return customerService.verify(customer);
    }

}

