package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity,Integer> {
}
