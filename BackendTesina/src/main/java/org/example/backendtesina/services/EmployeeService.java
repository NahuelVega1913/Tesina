package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetEmployee;
import org.example.backendtesina.DTOs.Post.PostEmployee;
import org.example.backendtesina.entities.EmployeeEntity;
import org.example.backendtesina.entities.enums.TypeOfContract;
import org.example.backendtesina.entities.enums.WorkinDayEntity;
import org.example.backendtesina.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public EmployeeEntity createEmploye(PostEmployee employee){
        EmployeeEntity entity = new EmployeeEntity();
        entity.setBancaryNumber(employee.getBancaryNumber());
        entity.setBirthDate(employee.getBirthDate());
        entity.setDateOfEntry(employee.getDateOfEntry());
        entity.setEmail(employee.getEmail());
        entity.setFullName(employee.getFullName());
        entity.setSalary(employee.getSalary());
        entity.setRemarks(employee.getRemarks());
        entity.setPosition(employee.getPosition());
        entity.setPhone(employee.getPhone());
        entity.setWorkingDay(WorkinDayEntity.valueOf(employee.getWorkingDay()));
        entity.setTypeOfContract(TypeOfContract.valueOf(employee.getTypeOfContract()));
        entity.setAddress(employee.getAddress());
        repository.save(entity);
        return entity;

    }
    public EmployeeEntity ModifyEmploye(PostEmployee employee){
        EmployeeEntity entity = repository.findById(employee.getId()).get();
        entity.setBancaryNumber(employee.getBancaryNumber());
        entity.setBirthDate(employee.getBirthDate());
        entity.setDateOfEntry(employee.getDateOfEntry());
        entity.setEmail(employee.getEmail());
        entity.setFullName(employee.getFullName());
        entity.setSalary(employee.getSalary());
        entity.setRemarks(employee.getRemarks());
        entity.setPosition(employee.getPosition());
        entity.setPhone(employee.getPhone());
        entity.setWorkingDay(WorkinDayEntity.valueOf(employee.getWorkingDay()));
        entity.setTypeOfContract(TypeOfContract.valueOf(employee.getTypeOfContract()));
        entity.setAddress(employee.getAddress());
        repository.save(entity);
        return entity;
    }
    public List<GetEmployee> getAll(){
        List<EmployeeEntity> lstEntity = repository.findAll();
        List<GetEmployee> lstEmploye = new ArrayList<>();
        if(lstEntity.isEmpty()){
            return null;
        }
        for (EmployeeEntity e:lstEntity){
            GetEmployee employee = new GetEmployee();
            employee.setFullName(e.getFullName());
            employee.setPosition(e.getPosition());
            employee.setPhone(e.getPhone());
            employee.setId(e.getId());
            lstEmploye.add(employee);
        }

        return lstEmploye;
    }
    public PostEmployee getEmployee(int id){
        PostEmployee entity = new PostEmployee();
        EmployeeEntity employee = repository.findById(id).get();
        entity.setBancaryNumber(employee.getBancaryNumber());
        entity.setBirthDate(employee.getBirthDate());
        entity.setId(employee.getId());
        entity.setDateOfEntry(employee.getDateOfEntry());
        entity.setEmail(employee.getEmail());
        entity.setFullName(employee.getFullName());
        entity.setSalary(employee.getSalary());
        entity.setRemarks(employee.getRemarks());
        entity.setPosition(employee.getPosition());
        entity.setPhone(employee.getPhone());
        entity.setWorkingDay(employee.getWorkingDay().toString());
        entity.setTypeOfContract(employee.getTypeOfContract().toString());
        entity.setAddress(employee.getAddress());
        return entity;
    }
}
