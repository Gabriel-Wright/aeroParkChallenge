package com.aeroPark.ApplicationCodingChallenge;

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import com.aeroPark.ApplicationCodingChallenge.service.CustomerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private static Customer customer;

    @BeforeAll
    public static void setUp(@Autowired CustomerService customerService) {
        customer = new Customer();
        customer.setEmailAddress("test@example.com");
        customer.setTitle("Mrs.");
        customer.setFirstName("Aero");
        customer.setLastName("Parker");
        customer.setAddressLine1("airport");
        customer.setPostcode("ABCDEFG");

        // Save the customer data using the CustomerService
        customerService.saveCustomer(customer);
    }

    @Test
    public void testIsEmailUnique_whenUniqueEmail_shouldReturnTrue() {
        // Test uniqueness of email
        boolean isUnique = customerService.isEmailUnique("test1@example.com");

        assertTrue(isUnique);
    }

    @Test
    public void testIsEmailUnique_whenNonUniqueEmail_shouldReturnFalse() {
        // Test non-uniqueness of email
        boolean isUniqueSameCase = customerService.isEmailUnique("test@example.com");
        boolean isUniqueDifferentCase = customerService.isEmailUnique("TEST@Example.com");
        assertFalse(isUniqueSameCase);
        assertFalse(isUniqueDifferentCase);
    }

    @AfterAll
    public static void cleanUp(@Autowired CustomerService customerService) {
        // Delete the test customer from the database
        customerService.removeCustomer(customer);
    }

}
