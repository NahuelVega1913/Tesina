package org.example.backendtesina.services;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.example.backendtesina.DTOs.Get.*;
import org.example.backendtesina.DTOs.Post.PostInspection;
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
import org.example.backendtesina.repositories.EmployeeRepository;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    NotificationService notificationService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JwtService jwtService;
    public ServiceEntity aceptBudget(int id){
        ServiceEntity service = repository.findById(id).orElse(null);
        if (service == null) {
            return null; // Si no se encuentra el servicio, retorna null
        }
        service.setStatus(ServiceStatus.WAITING); // Cambia el estado a "APPROVED"
        repository.save(service); // Guarda los cambios
        return service;
    }
    public ServiceEntity declineBudget(int id){
        ServiceEntity service = repository.findById(id).orElse(null);
        if (service == null) {
            return null; // Si no se encuentra el servicio, retorna null
        }

        service.setStatus(ServiceStatus.CANCELED); // Cambia el estado a "DECLINED"
        UserEntity user = service.getClient();
        List<ServiceEntity> lst = user.getServicios();
        for (ServiceEntity s :lst){
            if(s.getId() == service.getId()){
                s = null;
            }
        }
        userRepository.save(user);
        return service;
    }
    public ServiceEntity registerBudget(int id, Double budget){
        ServiceEntity service = repository.findById(id).orElse(null);
        if (service == null) {
            return null; // Si no se encuentra el servicio, retorna null
        }
        service.setBudget(budget); // Asigna el presupuesto al servicio
        service.setStatus(ServiceStatus.DECIDING); // Cambia el estado a "BUDGET"
        repository.save(service); // Guarda los cambios en la base de datos
        return service;
    }

    public InspectionEntity registerInspection(PostInspection inspection,String token){
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).get();
        InspectionEntity entity = new InspectionEntity();

        entity.setAuto(inspection.getAuto());
        entity.setType(TypeOfService.INSPECTION);
        entity.setPaymentStatus(PaymentStatus.UNPAID_DEPOSIT);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
        entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
        entity.setNombreCompleto(inspection.getNombreCompleto());
        entity.setStatus(ServiceStatus.BUDGET);
        entity.setClient(user);
        if(user.getServicios().isEmpty()){
            List<ServiceEntity> servicios = new ArrayList<>();
            servicios.add(entity);
            user.setServicios(servicios);
        }else{
            user.getServicios().add(entity);
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
        entity.setPaymentStatus(PaymentStatus.UNPAID_DEPOSIT);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
        entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
        entity.setNombreCompleto(inspection.getNombreCompleto());
        entity.setStatus(ServiceStatus.BUDGET);
        entity.setClient(user);
        if(user.getServicios().isEmpty()){
            List<ServiceEntity> servicios = new ArrayList<>();
            servicios.add(entity);
            user.setServicios(servicios);
        }else{
            user.getServicios().add(entity);
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
        entity.setPaymentStatus(PaymentStatus.UNPAID_DEPOSIT);
        entity.setDateEntry(LocalDateTime.now());
        entity.setModelo(inspection.getModelo());
        entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
        entity.setNombreCompleto(inspection.getNombreCompleto());
        entity.setStatus(ServiceStatus.BUDGET);
        entity.setClient(user);
        if(user.getServicios().isEmpty()){
            List<ServiceEntity> servicios = new ArrayList<>();
            servicios.add(entity);
            user.setServicios(servicios);
        }else{
            user.getServicios().add(entity);
        }

        repository.save(entity);
        return entity;
    }

    public String payMercadoPagoService(int id) throws MPException, MPApiException {
        ServiceEntity entity = repository.findById(id).get();
        entity.setPaymentStatus(PaymentStatus.PAID);
        repository.save(entity);
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id(String.valueOf(entity.getId()))
                        .title(entity.getAuto() + entity.getModelo())
                        .description(entity.getObservacionesPrevias())
                        .categoryId(entity.getType().toString())
                        .quantity(1)
                        .unitPrice(new BigDecimal(entity.getCost()))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://localhost:4200/repuestos")
                        .pending("https://localhost:4200/repuestos")
                        .failure("https://localhost:4200/repuestos")
                        .build();
        //PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .autoReturn("approved")
                .build();
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        return preference.getInitPoint();
    }
    public String payMercadoPagoSeña(int id) throws MPException, MPApiException {
        ServiceEntity entity = repository.findById(id).get();
        entity.setPaymentStatus(PaymentStatus.PAID_DEPOSIT);
        repository.save(entity);
        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id(String.valueOf(entity.getId()))
                        .title(entity.getAuto() + entity.getModelo())
                        .description("Seña del servicio")
                        .categoryId(entity.getType().toString())
                        .quantity(1)
                        .unitPrice(new BigDecimal(10000))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceBackUrlsRequest backUrls =
                PreferenceBackUrlsRequest.builder()
                        .success("https://localhost:4200/repuestos")
                        .pending("https://localhost:4200/repuestos")
                        .failure("https://localhost:4200/repuestos")
                        .build();
        //PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .autoReturn("approved")
                .build();
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        return preference.getInitPoint();
    }

    public ServiceEntity addEmployes(int id, int idEmployee){
        ServiceEntity service = repository.findById(id).get();
        List<EmployeeEntity> lstEntities = new ArrayList<>();
            service.setStatus(ServiceStatus.PROCESS);
            service.setPaymentStatus(PaymentStatus.UNPAID);
            EmployeeEntity entity = employeeRepository.findById(idEmployee).get();
            if(entity != null){
                if(entity.getJobs().isEmpty()){
                    entity.setJobs(new ArrayList<>());
                }
                entity.getJobs().add(service);
            }
            if(service.getEmpleados().isEmpty()){
                service.setEmpleados(new ArrayList<>());
            }
            service.getEmpleados().add(entity);

        repository.save(service);
        return service;
    }

    public InspectionEntity FinishInspection(GetInspection dto){
        InspectionEntity entity = (InspectionEntity) repository.findById(dto.getId()).get();
        entity.setEstadoGeneral(dto.getEstadoGeneral());
        entity.setRecomendaciones(dto.getRecomendaciones());
        entity.setResultado(dto.getResultado());
        entity.setCost(dto.getCost());
        entity.setStatus(ServiceStatus.FINISHED);
        notificationService.notificationServiceFinished(entity.getClient());
        repository.save(entity);
        return entity;
    }
    public ServiceEntity retireCard(int id){
        ServiceEntity entity = repository.findById(id).get();
        entity.setDateExit(LocalDateTime.now());
        entity.setStatus(ServiceStatus.WITHDRAW);
        repository.save(entity);
        return entity;
    }
    public RepairEntity FinishRepair(GetRepair dto){
        RepairEntity entity = (RepairEntity) repository.findById(dto.getId()).get();
        entity.setSparesUsed(dto.getSparesUsed());
        entity.setTasksPerformed(dto.getTasksPerformed());
        notificationService.notificationServiceFinished(entity.getClient());

        entity.setTechniclaDiagnosis(dto.getTechniclaDiagnosis());
        entity.setCost(dto.getCost());
        entity.setStatus(ServiceStatus.FINISHED);
        repository.save(entity);
        return entity;
    }
    public CustomizationEntity FinishCustomization(GetCustomization dto){
        CustomizationEntity entity = (CustomizationEntity) repository.findById(dto.getId()).get();
        entity.setMaterialsUsed(dto.getMaterialsUsed());
        entity.setTaskRealized(dto.getTaskRealized());
        entity.setCost(dto.getCost());
        notificationService.notificationServiceFinished(entity.getClient());
        entity.setStatus(ServiceStatus.FINISHED);
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
    public GetServices getService(int id){
       ServiceEntity inspectionEntity = repository.findById(id).get();

        if(inspectionEntity.getType().equals(TypeOfService.REPAIR)){
            RepairEntity inspection = (RepairEntity) repository.findById(id).get();
            GetRepair entity = new GetRepair();
            entity.setAuto(inspection.getAuto());
            entity.setType(inspection.getType().toString());
            entity.setPaymentStatus(inspection.getPaymentStatus());
            entity.setDateEntry(inspection.getDateEntry());
            entity.setModelo(inspection.getModelo());
            entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setStatus(inspection.getStatus());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setCost(inspection.getCost());
            entity.setId(inspection.getId());
            entity.setDateExit(inspection.getDateExit());
            if(inspection.getBudget() != null) {
                entity.setBudget(inspection.getBudget());
            }

            if(!inspection.getEmpleados().isEmpty()) {
                List<GetEmployee> lstEmployee = new ArrayList<>();
                for (EmployeeEntity e:inspection.getEmpleados()){
                    GetEmployee employeeDto = new GetEmployee();
                    employeeDto.setId(e.getId());
                    entity.setIdEmpleado(e.getId());

                    employeeDto.setPhone(e.getPhone());
                    employeeDto.setFullName(e.getFullName());
                    employeeDto.setPosition(e.getPosition());
                    lstEmployee.add(employeeDto);
                }
                entity.setEmpleados(lstEmployee);
            }

            entity.setSparesUsed(inspection.getSparesUsed());
            entity.setTechniclaDiagnosis(inspection.getTechniclaDiagnosis());
            entity.setTasksPerformed(inspection.getTasksPerformed());
            return entity;

        }
        if(inspectionEntity.getType().equals(TypeOfService.INSPECTION)){
            InspectionEntity inspection = (InspectionEntity) repository.findById(id).get();
            GetInspection entity = new GetInspection();
            entity.setAuto(inspection.getAuto());
            entity.setType(inspection.getType().toString());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setPaymentStatus(inspection.getPaymentStatus());
            entity.setDateEntry(inspection.getDateEntry());
            entity.setModelo(inspection.getModelo());
            entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setStatus(inspection.getStatus());
            entity.setCost(inspection.getCost());
            entity.setId(inspection.getId());
            if(inspection.getBudget() != null) {
                entity.setBudget(inspection.getBudget());
            }
            entity.setDateExit(inspection.getDateExit());
            if(!inspection.getEmpleados().isEmpty()) {
                List<GetEmployee> lstEmployee = new ArrayList<>();
                for (EmployeeEntity e:inspection.getEmpleados()){
                    GetEmployee employeeDto = new GetEmployee();
                    employeeDto.setId(e.getId());
                    entity.setIdEmpleado(e.getId());
                    employeeDto.setPhone(e.getPhone());
                    employeeDto.setFullName(e.getFullName());
                    employeeDto.setPosition(e.getPosition());
                    lstEmployee.add(employeeDto);
                }
                entity.setEmpleados(lstEmployee);
            }
            entity.setEstadoGeneral(inspection.getEstadoGeneral());
            entity.setRecomendaciones(inspection.getRecomendaciones());
            entity.setResultado(inspection.getResultado());
            return entity;
        }
        if(inspectionEntity.getType().equals(TypeOfService.CUSTOMIZATION)){
            CustomizationEntity inspection = (CustomizationEntity) repository.findById(id).get();
            GetCustomization entity = new GetCustomization();
            entity.setAuto(inspection.getAuto());
            entity.setType(inspection.getType().toString());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setPaymentStatus(inspection.getPaymentStatus());
            entity.setDateEntry(inspection.getDateEntry());
            entity.setModelo(inspection.getModelo());
            entity.setObservacionesPrevias(inspection.getObservacionesPrevias());
            entity.setNombreCompleto(inspection.getNombreCompleto());
            entity.setStatus(inspection.getStatus());
            entity.setCost(inspection.getCost());
            if(inspection.getBudget() != null) {
                entity.setBudget(inspection.getBudget());
            }
            entity.setId(inspection.getId());
            entity.setDateExit(inspection.getDateExit());
            if(!inspection.getEmpleados().isEmpty()) {
                List<GetEmployee> lstEmployee = new ArrayList<>();
                for (EmployeeEntity e:inspection.getEmpleados()){
                    GetEmployee employeeDto = new GetEmployee();
                    employeeDto.setId(e.getId());
                    entity.setIdEmpleado(e.getId());

                    employeeDto.setPhone(e.getPhone());
                    employeeDto.setFullName(e.getFullName());
                    employeeDto.setPosition(e.getPosition());
                    lstEmployee.add(employeeDto);
                }
                entity.setEmpleados(lstEmployee);
            }
            entity.setMaterialsUsed(inspection.getMaterialsUsed());
            entity.setTaskRealized(inspection.getTaskRealized());
            return entity;
        }
        return null;
    }
    public List<GetServices> getServices(){
        List<GetServices> lst = new ArrayList<>();
        List<ServiceEntity> lstEntity = this.repository.findAll();
        for (ServiceEntity s:lstEntity){
            GetServices get = new GetServices();
            get.setAuto(s.getAuto());
            get.setId(s.getId());
            get.setDateEntry(s.getDateEntry());
            get.setDateExit(s.getDateExit());
            get.setType(s.getType().toString());
            get.setModelo(s.getModelo());
            if(s.getBudget() != null){
            get.setBudget(s.getBudget());
            }
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
            get.setCost(s.getCost());

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
        entity.setPaymentStatus(PaymentStatus.PAID_DEPOSIT);
        entity.setDateEntry(LocalDateTime.now());
        repository.save(entity);
        return entity;
    }
    public ServiceEntity payService(int id){
        ServiceEntity entity =  this.repository.findById(id).get();
        entity.setStatus(ServiceStatus.WITHDRAW);
        entity.setPaymentStatus(PaymentStatus.PAID);
        entity.setDateExit(LocalDateTime.now());
        repository.save(entity);
        return entity;
    }

}
