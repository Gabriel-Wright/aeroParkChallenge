package com.aeroPark.ApplicationCodingChallenge.service;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.aeroPark.ApplicationCodingChallenge.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    public final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return customerRepository.findAll();
        } else {
            return customerRepository.search(filterText);
        }
    }

    //Case-insensitive check
    public boolean isEmailUnique(String emailAddress) {
        Optional<Customer> existingCustomer = customerRepository.findByEmailAddressIgnoreCase(emailAddress);
        //Return true if empty i.e. no customer - meaning email address is unique
        return existingCustomer.isEmpty();
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void removeCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
