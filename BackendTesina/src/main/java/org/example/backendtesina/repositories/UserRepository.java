package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.personal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, String> {
    // This interface will automatically provide CRUD operations for UserEntity
    // You can add custom query methods if needed
    Optional<UserEntity> findByEmail(String email);
}
