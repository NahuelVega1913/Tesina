package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.services.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeviceRepository extends JpaRepository<ServiceEntity,Integer> {
}
