package org.example.backendtesina.controllers;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.DTOs.Get.GetTurno;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @GetMapping( "/getall")
    public ResponseEntity<?> getAll(){
        List<GetTurno> turnos = turnoService.getAllTurnos();
        if (turnos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnos);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PostMapping( "/postturno")
    public ResponseEntity<?> postTurno(@RequestBody PostTurno turno){
        PostTurno createdTurno = turnoService.postTurno(turno);
        if (createdTurno == null) {
            return ResponseEntity.badRequest().body("Error al crear el turno");
        }
        return ResponseEntity.ok(createdTurno);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PutMapping( "/putturno")
    public ResponseEntity<?> putTurno(@RequestBody PostTurno turno){
        PostTurno createdTurno = turnoService.putTurno(turno);
        if (createdTurno == null) {
            return ResponseEntity.badRequest().body("Error al crear el turno");
        }
        return ResponseEntity.ok(createdTurno);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PutMapping("/cancelar")
    public ResponseEntity<?> cancelarTurno(@RequestBody PostTurno turno) {

           PostTurno response = turnoService.cancelarTurno(turno);
           if(response == null){
               return ResponseEntity.badRequest().body("Error al cancelar el turno");

           }
           return ResponseEntity.ok(response);


    }
}
