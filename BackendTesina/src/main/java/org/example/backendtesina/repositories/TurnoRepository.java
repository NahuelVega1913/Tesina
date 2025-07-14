package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.services.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoEntity,Integer> {
}
