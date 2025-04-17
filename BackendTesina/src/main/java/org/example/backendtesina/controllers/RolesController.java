package org.example.backendtesina.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;

@RestController
@RequestMapping("/roles")
public class RolesController {


    @GetMapping("/getAll")
    public int getAllRoles(){
        return 1;
    }
}
