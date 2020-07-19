package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for interacting with the database
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
