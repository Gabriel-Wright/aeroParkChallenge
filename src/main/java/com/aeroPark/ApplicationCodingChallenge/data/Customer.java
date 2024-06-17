package com.aeroPark.ApplicationCodingChallenge.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Each customer can be treated as an entity
//this class using data JPA can generate the customers table
@Entity
@Table(name = "customers")
public class Customer {
}
