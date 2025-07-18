package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.services.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.backendtesina.entities.personal.UserEntity;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<TurnoEntity,Integer> {
    Optional<TurnoEntity> findByUser(UserEntity user);

}
