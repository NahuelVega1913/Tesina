package org.example.backendtesina.repositories;

import com.thoughtworks.qdox.model.JavaPackage;
import org.example.backendtesina.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeviceRepository extends JpaRepository<ServiceEntity,Integer> {
}
