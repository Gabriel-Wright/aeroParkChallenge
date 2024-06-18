package com.aeroPark.ApplicationCodingChallenge.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAddressIgnoreCase(String emailAddress);

    // Searches by first name, email, or surname similar to the filterText
    @Query("SELECT c FROM Customer c WHERE lower(c.firstName) LIKE %:filterText% OR lower(c.emailAddress) LIKE %:filterText% OR lower(c.lastName) LIKE %:filterText%")
    List<Customer> search(@Param("filterText") String filterText);

}
