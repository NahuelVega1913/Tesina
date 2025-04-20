package org.example.backendtesina.controllers;

import org.example.backendtesina.entities.RoleEntity;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {


//    @GetMapping("/getAll")
//    public List<String> getAllRoles(){
//        List<String> roles = null;
//        for(RoleEntity role : RoleEntity.values()){
//            roles.add(role.toString());
//        }
//        return roles;
//    }
    
}
