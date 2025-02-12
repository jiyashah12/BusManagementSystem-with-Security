package com.example.BusManagementSystem.services;

import com.example.BusManagementSystem.entities.Customer;
import com.example.BusManagementSystem.entities.Roles;
import com.example.BusManagementSystem.repositories.CustomerRepository;
import com.example.BusManagementSystem.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributeIdentifierException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder bcpEncoder = new BCryptPasswordEncoder(12);

    @Override
    public void addCustomer(Customer customer) {

        customer.setPassword(bcpEncoder.encode(customer.getPassword()));
//        customer.setRoles(Roles.valueOf("USER"));
//        customer.setRoles(Roles.USER);
//        Roles userRole = new Roles();
//        userRole.setRole_name("USER");
//        customer.setRoles(List.of(userRole));

        Roles role = roleRepository.findById(1L).get();
        customer.setRole(role);

//        Roles role = roleRepository.findById(customer.getRole().getId())
//                .orElseThrow(() -> new RuntimeException("Role not found!"));

        // Set the fetched role in the customer object
        customer.setRole(role);
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long cust_id) {
        Optional<Customer> customerOptional = customerRepository.findById(cust_id);
        return customerOptional.get();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    @Override
    public void deleteCustomerById(long cust_id) {
        customerRepository.deleteById(cust_id);
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }


    @Override
    public String verify(Customer customer) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));

        if(authentication.isAuthenticated()){
//            return "Success";
            return jwtService.generateToken(customer.getUsername());
        }
        return "Fail";
//        return "";
    }
}



