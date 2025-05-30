package org.example.backendtesina.entities.personal;

import jakarta.persistence.*;
import org.example.backendtesina.entities.enums.TypeOfContract;
import org.example.backendtesina.entities.enums.WorkinDayEntity;
import org.example.backendtesina.entities.services.ServiceEntity;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "BIRTHDATE")
    private Date birthDate;
    @Column(name = "CUIT")
    private int CUIT;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPEOFCONTRACT")
    private TypeOfContract typeOfContract;
    @Enumerated(EnumType.STRING)
    @Column(name = "WORKINGDAY")
    private WorkinDayEntity workingDay;
    @Column(name = "BANCARYNUMBER")
    private int bancaryNumber;
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "SALARY")
    private Double salary;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "DATEOFENTRY")
    private Date dateOfEntry;
    @Column(name = "REMARKS")
    private String remarks;
    @OneToMany
    private List<ServiceEntity> jobs;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public TypeOfContract getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(TypeOfContract typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public WorkinDayEntity getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(WorkinDayEntity workingDay) {
        this.workingDay = workingDay;
    }

    public int getBancaryNumber() {
        return bancaryNumber;
    }

    public void setBancaryNumber(int bancaryNumber) {
        this.bancaryNumber = bancaryNumber;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCUIT() {
        return CUIT;
    }

    public void setCUIT(int CUIT) {
        this.CUIT = CUIT;
    }

    public List<ServiceEntity> getJobs() {
        return jobs;
    }

    public void setJobs(List<ServiceEntity> jobs) {
        this.jobs = jobs;
    }
}
