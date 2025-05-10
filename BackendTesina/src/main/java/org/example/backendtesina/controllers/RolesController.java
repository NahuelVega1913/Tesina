package org.example.backendtesina.controllers;

import org.example.backendtesina.entities.enums.RoleEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @GetMapping("/getAll")
    public RoleEntity[] getAllRoles(){
        return RoleEntity.values();
    }
    
}
