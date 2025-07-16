package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;


    @GetMapping(value = "getAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public ResponseEntity<?> getAll(){
        List<PostTurno> turnos = turnoService.getAllTurnos();
        if (turnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnos);
    }



    @PostMapping(value = "postTurno")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public ResponseEntity<?> postTurno(@RequestBody PostTurno turno){
        TurnoEntity createdTurno = turnoService.postTurno(turno);
        if (createdTurno == null) {
            return ResponseEntity.badRequest().body("Error al crear el turno");
        }
        return ResponseEntity.ok(createdTurno);
    }
    @PutMapping(value = "putTurno")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public ResponseEntity<?> putTurno(@RequestBody PostTurno turno){
        TurnoEntity createdTurno = turnoService.putTurno(turno);
        if (createdTurno == null) {
            return ResponseEntity.badRequest().body("Error al crear el turno");
        }
        return ResponseEntity.ok(createdTurno);
    }
}
