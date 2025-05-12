package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.payment.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    // Define custom query methods if needed
    // For example, find by user ID or other criteria


}
