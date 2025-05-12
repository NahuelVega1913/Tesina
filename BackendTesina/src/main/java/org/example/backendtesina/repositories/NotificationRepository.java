package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.personal.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,Integer> {
}
