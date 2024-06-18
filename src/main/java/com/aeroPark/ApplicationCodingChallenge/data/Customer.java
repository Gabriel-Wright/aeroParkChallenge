package com.aeroPark.ApplicationCodingChallenge.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

//Each customer can be treated as an entity
//this class using data JPA can generate the customers table
@Entity
////These lombok annotations should give getters, setters and make constructors
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "customers")
public class Customer {


    //Will auto increment IDs of generated customers
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Will automatically assign dateTime when registered
    @Column(updatable = false)
    private LocalDateTime registered;

    @NotBlank(message = "A valid email address is required")
    @Email(message = "This must be a Valid email address")
    @Size(max = 255, message = "The email address cannot exceed 255 characters in length")
    @Column(unique = true)
    private String emailAddress;

    @NotBlank(message = "Please select a title")
    @Size(max = 5)
    private String title;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "This first name cannot exceed 50 characters in length")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "This last name cannot exceed 50 characters in length")
    private String lastName;

    @NotBlank(message = "First line of address is required")
    @Size(max = 255, message = "This address line cannot exceed 255 characters in length")
    private String addressLine1;

    @Size(max = 255, message = "This address line cannot exceed 255 characters in length")
    private String addressLine2;

    @Size(max = 255, message = "This city cannot exceed 255 characters in length")
    private String city;

    @NotBlank(message = "A postcode is required")
    @Size(max = 10, message = "This postcode cannot exceed 10 characters in length")
    private String postcode;

    @Size(max = 20, message = "This phone number cannot exceed 20 characters in length")
    private String phoneNumber;

    //Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //For initialising registered
    @PrePersist
    protected void onCreate() {
        this.registered = LocalDateTime.now();
    }

}
