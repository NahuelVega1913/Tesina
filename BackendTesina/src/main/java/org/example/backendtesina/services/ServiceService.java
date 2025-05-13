package org.example.backendtesina.services;

import org.apache.catalina.User;
import org.example.backendtesina.DTOs.Get.GetStatusService;
import org.example.backendtesina.DTOs.Post.PostInspection;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.InspectionEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    SeviceRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;

    public InspectionEntity registerInspection(PostInspection inspection,String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).get();
        InspectionEntity entity = new InspectionEntity();

        entity.setAuto(inspection.getAuto());
        entity.setDateEntry(LocalDateTime.now());
        entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
        entity.setNombreCompleto(inspection.getNombreCompleto());
        entity.setClient(user);
        if(user.getServicios().isEmpty()){
            List<ServiceEntity> servicios = new ArrayList<>();
            servicios.add(entity);
            user.setServicios(servicios);
        }else{
            List<ServiceEntity> servicios = user.getServicios();
            servicios.add(entity);
            user.setServicios(servicios);
        }


        repository.save(entity);
        return entity;
    }
    public GetStatusService getStatusService(String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).get();
        ServiceEntity entity = user.getServicios().get((user.getServicios().size()) - 1);
        GetStatusService status = new GetStatusService();
        status.setStatus(entity.getStatus());
        status.setId(entity.getId());
        return status;
    }
}
