package org.example.backendtesina.repositories;

import org.example.backendtesina.entities.payment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
}
