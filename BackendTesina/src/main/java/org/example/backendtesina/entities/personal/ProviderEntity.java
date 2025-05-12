package org.example.backendtesina.entities.personal;

import jakarta.persistence.*;
import org.example.backendtesina.entities.payment.SpareEntity;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.enums.CountryEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROVIDERS")
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String adress;
    @Enumerated(EnumType.STRING)
    private CategorySpareEntity category;

    private String city;
    @Enumerated(EnumType.STRING)
    private CountryEntity country;

    @OneToMany(mappedBy = "provider")
    private List<SpareEntity> spares;

    private String CUIT;
    private String email;
    private BigInteger phone;
    private boolean state;

    private Date registerDate;
    private String remarks;

    public ProviderEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public CategorySpareEntity getCategory() {
        return category;
    }

    public void setCategory(CategorySpareEntity category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<SpareEntity> getSpares() {
        return spares;
    }

    public void setSpares(List<SpareEntity> repuestos) {
        this.spares = repuestos;
    }
}
