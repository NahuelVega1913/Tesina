package org.example.backendtesina.entities.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.context.annotation.Role;

import java.util.List;


public enum RoleEntity {
    SUPERADMIN,
    ADMIN,
    USER,
    EMPLOYEE

}
