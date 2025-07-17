package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import org.example.backendtesina.DTOs.Get.GetTurno;

@Service
public class TurnoService {


    @Autowired
    TurnoRepository repository;
    @Autowired
    TurnoRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    SeviceRepository serviceRepository;



    public List<GetTurno> getAllTurnos(){
        List<TurnoEntity> entities = repository.findAll();
        List<GetTurno> dtos = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sixDaysLater = now.plusDays(6);

        for (TurnoEntity turno : entities) {
            if (turno.getHoraInicio().isAfter(now) && turno.getHoraInicio().isBefore(sixDaysLater)) {
                GetTurno dto = new GetTurno();
                dto.setFecha(java.sql.Date.valueOf(turno.getHoraInicio().toLocalDate()));
                dto.setHoraInicio(turno.getHoraInicio().toLocalTime().toString());
                dto.setHoraFin(turno.getHoraFin() != null ? turno.getHoraFin().toLocalTime().toString() : null);

                // Calcular lugares libres
                long turnosEnElDia = entities.stream()
                        .filter(t -> t.getHoraInicio().toLocalDate().equals(turno.getHoraInicio().toLocalDate()))
                        .count();
                dto.setLugaresLibres(1 - (int) turnosEnElDia);

                dtos.add(dto);
            }
        }

        return dtos;

    }
    public PostTurno postTurno(PostTurno turno){
        try {
            TurnoEntity entity = new TurnoEntity();
            entity.setEstado(turno.getEstado());
            entity.setHoraInicio(turno.getHoraInicio());
            entity.setHoraFin(turno.getHoraFin());


            UserEntity user = userService.getEntity(turno.getEmailUser());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
            }
            entity.setUser(user);

            ServiceEntity service = serviceRepository.findById(turno.getServiceId()).orElse(null);
            if (service == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado");
            }
            entity.setService(service);

             repository.save(entity);
            PostTurno p = new PostTurno();
            p.setId(entity.getId());
            p.setEstado(entity.getEstado());
            p.setServiceId(entity.getService().getId());
            p.setHoraInicio(entity.getHoraInicio());
            if(entity.getHoraFin() != null){
                p.setHoraFin(entity.getHoraFin());
            }
            p.setEmailUser(entity.getUser().getEmail());
            return p;


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar turno: " + ex.getMessage());
        }
    }
    public PostTurno putTurno(PostTurno turno){
        TurnoEntity existingTurno = repository.findById(turno.getId()).orElse(null);
        if (existingTurno == null) {
            return null; // Si el turno no existe, retorna null
        }

        // Actualizar los campos del turno
        existingTurno.setEstado(turno.getEstado());
        existingTurno.setHoraInicio(turno.getHoraInicio());
        existingTurno.setHoraFin(turno.getHoraFin());

        // Actualizar usuario si se proporciona un email
        if (turno.getEmailUser() != null) {
            UserEntity user = userService.getEntity(turno.getEmailUser());
            if (user != null) {
                existingTurno.setUser(user);
            }
        }

        // Actualizar servicio si se proporciona un ID
        if (turno.getServiceId() != null) {
            ServiceEntity service = serviceRepository.findById(turno.getServiceId()).orElse(null);
            if (service != null) {
                existingTurno.setService(service);
            }
        }

        repository.save(existingTurno);
        PostTurno p = new PostTurno();
        p.setId(existingTurno.getId());
        p.setEstado(existingTurno.getEstado());
        p.setServiceId(existingTurno.getService().getId());
        p.setHoraInicio(existingTurno.getHoraInicio());
        if(existingTurno.getHoraFin() != null){
            p.setHoraFin(existingTurno.getHoraFin());
        }
        p.setEmailUser(existingTurno.getUser().getEmail());
        return p;

    }
}
