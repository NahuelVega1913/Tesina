package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.personal.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity,Integer> {
}
