package com.aeroPark.ApplicationCodingChallenge;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.aeroPark.ApplicationCodingChallenge.data.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    //Need to handle how this case with saving won't interfere with live databases - maybe in springboot .test profile active h2?
    @Test
    public void testUniqueEmailConstraint() {
        //Prepare a customer with a valid email address
        Customer customer = new Customer();
        customer.setEmailAddress("test@example.com");
        customer.setTitle("Mr.");
        customer.setFirstName("Aero");
        customer.setLastName("Parker");
        customer.setAddressLine1("AeroParker airport");
        customer.setPostcode("ABCD1234");

        // Save the first customer
        customerRepository.save(customer);

        //Attempt to save another customer with same email address

        Customer customer2 = new Customer();
        customer2.setEmailAddress("test@example.com");
        customer2.setTitle("Mrs.");
        customer2.setFirstName("Ground");
        customer2.setLastName("Mover");
        customer2.setAddressLine1("Underground");
        customer2.setPostcode("ZYXW98765");

        // Assert that saving customer2 throws DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(customer2),
                "Expected DataIntegrityViolationException due to unique constraint violation");
    }

    @Test
    public void testRegisteredTimestampInitailisation() {
        Customer customer = new Customer();
        customer.setEmailAddress("testTime@example.com");
        customer.setTitle("Mr.");
        customer.setFirstName("Aero");
        customer.setLastName("Parker");
        customer.setAddressLine1("AeroParker airport");
        customer.setPostcode("ABCD1234");

        //Save the customer in repo - this should assign an update to date time
        customerRepository.save(customer);

        //Get customer in repo based on previously saved id
        Customer savedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNotNull(savedCustomer, "Customer should be saved in the database");

        //check that time is assigned
        assertNotNull(savedCustomer.getRegistered(), "Registered timestamp not null");
        //Check if assigned time is accurate by checking that the time is relatively close to time now
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime registeredTime = savedCustomer.getRegistered();
        assertTrue(registeredTime.isBefore(now.plusSeconds(1)),"Registered timestamp should be close" +
                "to current time");
        assertTrue(registeredTime.isAfter(now.minusSeconds(1)),"Registered timestamp should be close" +
                "to current time");

    }
}

