package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Customer;
import com.example.BusManagementSystem.entities.CustomerPrincipal;
import com.example.BusManagementSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository custRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer cust1 = custRepo.findByUsername(username);

        if(cust1 == null){
            System.out.println("Cust not found.");
            throw new RuntimeException("Customer not found.");
        }

//        return null;
        return new CustomerPrincipal(cust1);
    }
}



