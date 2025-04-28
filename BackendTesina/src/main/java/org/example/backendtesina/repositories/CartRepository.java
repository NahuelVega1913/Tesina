package org.example.backendtesina.repositories;

import ch.qos.logback.core.model.INamedModel;
import org.example.backendtesina.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    // Define custom query methods if needed
    // For example, find by user ID or other criteria


}
