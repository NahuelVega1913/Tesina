package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetInspection;
import org.example.backendtesina.DTOs.Get.GetServices;
import org.example.backendtesina.DTOs.Get.GetStatusService;
import org.example.backendtesina.DTOs.Post.PostInspection;
import org.example.backendtesina.entities.enums.ServiceStatus;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.InspectionEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        entity.setStatus(ServiceStatus.WAITING);
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
    public GetInspection getInspection(){
        return null;
    }
    public List<GetServices> getServices(){
        List<GetServices> lst = new ArrayList<>();
        List<ServiceEntity> lstEntity = this.repository.findAll();


        return null;
    }

}
