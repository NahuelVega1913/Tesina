package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, String> {
    // This interface will automatically provide CRUD operations for UserEntity
    // You can add custom query methods if needed
}
