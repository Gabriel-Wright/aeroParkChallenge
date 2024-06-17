package com.aeroPark.ApplicationCodingChallenge;

//Test class used to ensure that appropriate or inappropriate data will be accepted or rejected accordingly.

import com.aeroPark.ApplicationCodingChallenge.data.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerValidationTests {

    //Used to check valid types
    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;

    @Test
    public void testValidCustomer() {
        Customer customer = new Customer();
        customer.setEmailAddress("test@example.com");
        customer.setTitle("Mr.");
        customer.setFirstName("Aero");
        customer.setLastName("Parker");
        customer.setAddressLine1("AeroParker airport");
        customer.setPostcode("ABCD1234");

        var violations = validatorFactoryBean.validate(customer);

        assertTrue(violations.isEmpty(), "Validation should pass for a valid customer");
    }

    //Case where customer no necessary fields are inserted
    @Test
    public void testInvalidCustomerBlankEntry() {
        Customer customer = new Customer();
        customer.setEmailAddress("");
        //Title set to blank
        customer.setTitle("");
        customer.setFirstName("");
        customer.setLastName("");
        customer.setAddressLine1("");
        customer.setPostcode("");

        var violations = validatorFactoryBean.validate(customer);
        int numViolations = violations.size();
        assertFalse(violations.isEmpty(), "Validation should fail for blank fields");
        //Expected 6 required fields
        assertEquals(6, numViolations);
    }

    @Test
    public void testInvalidEmail() {
        Customer customer = new Customer();
        customer.setEmailAddress("invalid");
        customer.setTitle("Mr.");
        customer.setFirstName("Aero");
        customer.setLastName("Parker");
        customer.setAddressLine1("AeroParker airport");
        customer.setPostcode("ABCD1234");

        var violations = validatorFactoryBean.validate(customer);
        assertFalse(violations.isEmpty(), "Validation should fail for invalid email addresses");
    }

    @Test
    public void testTooLongFieldEntries() {
        Customer customer = new Customer();
        customer.setEmailAddress("test@example.com");
        customer.setTitle("InvalidTitleThatIsTooLong"); // Exceeds @Size(max = 5)
        customer.setFirstName("Aero name that is too long for limit, cannot exceed 50 characters"); // Exceeds @Size(max = 50)
        customer.setLastName("Parker");
        customer.setAddressLine1("AeroParker airport ");
        customer.setPostcode("ABCD1234");

        var violations = validatorFactoryBean.validate(customer);
        int numViolations = violations.size();

        assertFalse(violations.isEmpty(), "Validation should fail for fields that exceed maximum length");
        assertEquals(2, numViolations);
    }
}

