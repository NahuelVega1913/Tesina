package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Post.PostTurno;
import org.example.backendtesina.DTOs.Post.PutTurno;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.entities.services.ServiceEntity;
import org.example.backendtesina.entities.services.TurnoEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.SeviceRepository;
import org.example.backendtesina.repositories.TurnoRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalTime;
import java.time.LocalDate;
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
    UserService userService;
    @Autowired
    SeviceRepository serviceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;



    public List<GetTurno> getAllTurnos() {
        List<TurnoEntity> entities = repository.findAll();
        List<GetTurno> dtos = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sixDaysLater = now.plusDays(6);

        // Intervalos fijos
        LocalTime[] intervalosInicio = {LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0), LocalTime.of(16, 0)};
        LocalTime[] intervalosFin = {LocalTime.of(11, 0), LocalTime.of(13, 0), LocalTime.of(16, 0), LocalTime.of(18, 0)};

        // Mapa para rastrear la disponibilidad por horario
        Map<LocalDateTime, Integer> disponibilidadPorHorario = new HashMap<>();

        // Inicializar disponibilidad para los próximos 6 días
        for (int i = 0; i <= 6; i++) {
            LocalDate fecha = now.toLocalDate().plusDays(i);
            if (fecha.getDayOfWeek() != java.time.DayOfWeek.SATURDAY && fecha.getDayOfWeek() != java.time.DayOfWeek.SUNDAY) {
                for (int j = 0; j < intervalosInicio.length; j++) {
                    LocalDateTime inicioIntervalo = fecha.atTime(intervalosInicio[j]);
                    disponibilidadPorHorario.put(inicioIntervalo, 5); // Inicialmente 5 lugares disponibles
                }
            }
        }

        // Procesar los turnos existentes
        for (TurnoEntity turno : entities) {
            if (!"CANCELADO".equals(turno.getEstado()) &&
                    !"FINALIZADO".equals(turno.getEstado()) &&
                    turno.getHoraInicio().isAfter(now) &&
                    turno.getHoraInicio().isBefore(sixDaysLater)) {

                LocalDateTime inicioTurno = turno.getHoraInicio();
                LocalDateTime finTurno = turno.getHoraInicio().plusDays(1).withHour(18).withMinute(0);

                // Reducir lugares disponibles desde el horario reservado hasta el final del día siguiente
                while (!inicioTurno.isAfter(finTurno)) {
                    if (inicioTurno.getDayOfWeek() != java.time.DayOfWeek.SATURDAY &&
                            inicioTurno.getDayOfWeek() != java.time.DayOfWeek.SUNDAY) {

                        for (int i = 0; i < intervalosInicio.length; i++) {
                            LocalDateTime inicioIntervalo = inicioTurno.toLocalDate().atTime(intervalosInicio[i]);
                            if (!inicioIntervalo.isBefore(turno.getHoraInicio())) {
                                disponibilidadPorHorario.put(inicioIntervalo,
                                        disponibilidadPorHorario.getOrDefault(inicioIntervalo, 5) - 1);
                            }
                        }
                    }
                    inicioTurno = inicioTurno.plusDays(1).withHour(9).withMinute(0);
                }
            }
        }

        // Crear los GetTurno para los horarios disponibles
        for (Map.Entry<LocalDateTime, Integer> entry : disponibilidadPorHorario.entrySet()) {
            LocalDateTime inicioIntervalo = entry.getKey();
            int lugaresLibres = entry.getValue();
            if (lugaresLibres > 0) {
                GetTurno dto = new GetTurno();
                dto.setFecha(java.sql.Date.valueOf(inicioIntervalo.toLocalDate()));
                dto.setHoraInicio(inicioIntervalo.toLocalTime().toString());
                dto.setHoraFin(inicioIntervalo.plusHours(2).toLocalTime().toString());
                dto.setLugaresLibres(lugaresLibres);
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
    public PostTurno cancelarTurno(String token) {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        // Buscar el turno asociado al usuario
        TurnoEntity turno = repository.findByUser(user).orElse(null);
        PostTurno dto = new PostTurno();
        dto.setId(turno.getId());
        dto.setServiceId(turno.getService().getId());
        dto.setEmailUser(turno.getUser().getEmail());
        dto.setHoraInicio(turno.getHoraInicio());
        dto.setHoraFin(turno.getHoraFin());

        if (turno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tienes turnos activos para cancelar");
        }

        // Actualizar el estado del turno a "CANCELADO" y eliminar la relación con el usuario
        turno.setEstado("CANCELADO");
        turno.setUser(null);


        // Guardar los cambios
        repository.save(turno);
        return  dto;
    }
    public PostTurno finalizarTurno(int turnoId) {
        // Buscar el turno por ID
        TurnoEntity turno = repository.findById(turnoId).orElse(null);

        if (turno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado");
        }

        // Verificar si el turno ya está finalizado
        if ("FINALIZADO".equals(turno.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El turno ya está finalizado");
        }

        // Actualizar el estado del turno a "FINALIZADO"
        turno.setEstado("FINALIZADO");

        // Guardar los cambios
        repository.save(turno);

        // Crear y devolver el DTO de respuesta
        PostTurno dto = new PostTurno();
        dto.setId(turno.getId());
        dto.setEstado(turno.getEstado());
        dto.setServiceId(turno.getService().getId());
        dto.setEmailUser(turno.getUser() != null ? turno.getUser().getEmail() : null);
        dto.setHoraInicio(turno.getHoraInicio());
        dto.setHoraFin(turno.getHoraFin());

        return dto;
    }
    public List<PutTurno> getTurnosDeHoy() {
        LocalDateTime inicioDia = LocalDate.now().atStartOfDay();
        LocalDateTime finDia = inicioDia.plusDays(1).minusSeconds(1);

        List<TurnoEntity> turnosDeHoy = repository.findAllByHoraInicioBetween(inicioDia, finDia);
        List<PutTurno> dtos = new ArrayList<>();

        for (TurnoEntity turno : turnosDeHoy) {
            PutTurno dto = new PutTurno();
            dto.setId(turno.getId());
            dto.setFecha(java.sql.Date.valueOf(turno.getHoraInicio().toLocalDate()));
            dto.setHoraInicio(turno.getHoraInicio());
            dto.setHoraFin(turno.getHoraFin());
            dto.setEstado(turno.getEstado());
            dto.setNombreUser(turno.getUser() != null ? turno.getUser().getName() : null);
            dto.setServiceId(turno.getService() != null ? turno.getService().getId() : null);
            dtos.add(dto);
        }

        return dtos;
    }
}
