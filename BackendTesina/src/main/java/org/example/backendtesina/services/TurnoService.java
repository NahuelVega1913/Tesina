package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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



    public List<PostTurno> getAllTurnos(){
        List<TurnoEntity> entities = repository.findAll();
        List<PostTurno> dtos = new ArrayList<>();
        for (TurnoEntity x:entities){
            PostTurno p = new PostTurno();
            p.setEstado(x.getEstado());
            p.setServiceId(x.getService().getId());
            p.setHoraInicio(x.getHoraInicio());
            if(x.getHoraFin() != null){
                p.setHoraFin(x.getHoraFin());
            }
             p.setEmailUser(x.getUser().getEmail());
            dtos.add(p);
        }
        return dtos;

    }
    public TurnoEntity postTurno(PostTurno turno){
        TurnoEntity entity = new TurnoEntity();
        entity.setEstado(turno.getEstado());
        entity.setHoraInicio(turno.getHoraInicio());
        entity.setHoraFin(turno.getHoraFin());

        // Asignar relaciones con usuario y servicio
        UserEntity user = userService.getEntity(turno.getEmailUser());
        if (user == null) {
            return null; // Si el usuario no existe, retorna null
        }
        entity.setUser(user);

        ServiceEntity service = serviceRepository.findById(turno.getServiceId()).orElse(null);
        if (service == null) {
            return null; // Si el servicio no existe, retorna null
        }
        entity.setService(service);

        return repository.save(entity); // Guarda el turno en la base de datos
    }
    public TurnoEntity putTurno(PostTurno turno){
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

        return repository.save(existingTurno);
    }
}
