package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.payment.SpareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpareRepository extends JpaRepository<SpareEntity, Integer> {
    // Custom query methods can be defined here if needed
}
