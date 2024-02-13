package com.example.support_management.repository;

import com.example.support_management.model.Customer;
import com.example.support_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(Integer id);
}
