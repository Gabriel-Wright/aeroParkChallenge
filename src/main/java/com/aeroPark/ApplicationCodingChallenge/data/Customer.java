package com.aeroPark.ApplicationCodingChallenge.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDateTime;

//Each customer can be treated as an entity
//this class using data JPA can generate the customers table
@Entity
//These lombok annotations should give getters, setters and make constructors
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {


    //Will auto increment IDs of generated customers
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Will automatically assign dateTime when registered
    private LocalDateTime registered;

    @NotBlank
    @Email
    @Unique
    @Size(max = 255)
    private String emailAddress;

    @NotBlank
    @Size(max = 5)
    private String title;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 255)
    private String addressLine1;

    @Size(max = 255)
    private String addressLine2;

    @Size(max = 255)
    private String city;

    @NotBlank
    @Size(max = 10)
    private String postcode;

    @Size(max = 20)
    private String phoneNumber;
}
