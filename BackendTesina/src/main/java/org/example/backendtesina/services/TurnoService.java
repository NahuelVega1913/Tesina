package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {


    @Autowired
    TurnoRepository repository;


    public PostTurno getAllTurnos(){
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
            p.setUserid(x.getUser().getEmail());
            p.set
        }
        return null;

    }
    void postTurno(){

    }
    void deleteTurno(){

    }
}
