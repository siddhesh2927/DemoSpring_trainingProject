package com.example.demo.Repository;

import com.example.demo.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

//    finalall findbyId save deletebyId count

    Optional<Customer> findByEmail(String email);

    List<Customer> findByCity(String city);

    List<Customer> findByAgeGreaterThan(int age);




}