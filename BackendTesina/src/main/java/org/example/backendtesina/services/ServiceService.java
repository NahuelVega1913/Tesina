package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetEmployee;
import org.example.backendtesina.DTOs.Get.GetInspection;
import org.example.backendtesina.DTOs.Get.GetServices;
import org.example.backendtesina.DTOs.Get.GetStatusService;
import org.example.backendtesina.DTOs.Post.PostCustomization;
import org.example.backendtesina.DTOs.Post.PostInspection;
import org.example.backendtesina.DTOs.Post.PostRepair;
import org.example.backendtesina.entities.enums.PaymentStatus;
import org.example.backendtesina.entities.enums.ServiceStatus;
import org.example.backendtesina.entities.enums.TypeOfService;
import org.example.backendtesina.entities.personal.EmployeeEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.CustomizationEntity;
import org.example.backendtesina.entities.services.InspectionEntity;
import org.example.backendtesina.entities.services.RepairEntity;
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
        entity.setType(TypeOfService.INSPECTION);
        entity.setPaymentStatus(PaymentStatus.UNPAID);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
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

    public RepairEntity registerRepair(PostInspection inspection, String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).get();
        RepairEntity entity = new RepairEntity();
        entity.setAuto(inspection.getAuto());
        entity.setType(TypeOfService.REPAIR);
        entity.setPaymentStatus(PaymentStatus.UNPAID);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
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
    public CustomizationEntity registerCustomization(PostInspection inspection, String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).get();
        CustomizationEntity entity = new CustomizationEntity();
        entity.setAuto(inspection.getAuto());
        entity.setType(TypeOfService.CUSTOMIZATION);
        entity.setPaymentStatus(PaymentStatus.UNPAID);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
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
        for (ServiceEntity s:lstEntity){
            GetServices get = new GetServices();
            get.setAuto(s.getAuto());
            get.setId(s.getId());
            get.setType(s.getType().toString());
            get.setModelo(s.getModelo());
            if(!s.getEmpleados().isEmpty()) {
                List<GetEmployee> lstEmployee = new ArrayList<>();
                for (EmployeeEntity e:s.getEmpleados()){
                    GetEmployee employeeDto = new GetEmployee();
                    employeeDto.setId(e.getId());
                    employeeDto.setPhone(e.getPhone());
                    employeeDto.setFullName(e.getFullName());
                    employeeDto.setPosition(e.getPosition());
                    lstEmployee.add(employeeDto);
                }
                get.setEmpleados(lstEmployee);
            }
            get.setStatus(s.getStatus());
            get.setNombreCompleto(s.getNombreCompleto());
            get.setObservacionesPrevias(s.getObservacionesPrevias());
            get.setPaymentStatus(s.getPaymentStatus());
            lst.add(get);
        }
        return lst;
    }

    public ServiceEntity registerEntry(int id){
        ServiceEntity entity = repository.findById(id).get();
        if(entity == null){
            return null;
        }
        entity.setStatus(ServiceStatus.IN_QUEUE);
        entity.setDateEntry(LocalDateTime.now());
        repository.save(entity);
        return entity;
    }

}
