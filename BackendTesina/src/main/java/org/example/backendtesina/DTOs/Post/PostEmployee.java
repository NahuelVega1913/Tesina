package org.example.backendtesina.DTOs.Post;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.backendtesina.entities.enums.TypeOfContract;
import org.example.backendtesina.entities.enums.WorkinDayEntity;

import java.util.Date;

public class PostEmployee {
    private int id;
    private Date birthDate;

    private String typeOfContract;
    private int CUIT;
    private String workingDay;

    private String fullName;
    private Double salary;
    private String address;
    private String phone;
    private String email;
    private int bancaryNumber;
    private String position;
    private String dateOfEntry;
    private String remarks;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBancaryNumber() {
        return bancaryNumber;
    }

    public void setBancaryNumber(int bancaryNumber) {
        this.bancaryNumber = bancaryNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCUIT() {
        return CUIT;
    }

    public void setCUIT(int CUIT) {
        this.CUIT = CUIT;
    }
}
