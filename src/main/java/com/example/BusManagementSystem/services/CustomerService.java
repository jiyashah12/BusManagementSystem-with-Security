package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Customer;
import java.util.List;

public interface CustomerService {

    public void addCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(long cust_id);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomerById(long hotel_id);

    public void deleteAllCustomers();

    public Customer getCustomerByEmail(String email);


    String verify(Customer customer);
}
