package org.example.backendtesina.DTOs.Post;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.enums.CityEntity;
import org.example.backendtesina.entities.enums.CountryEntity;

import java.math.BigInteger;
import java.util.Date;

public class PostProviderDTO {

    private Integer id;
    private String name;
    private String adress;


    private CategorySpareEntity category;

    private String city;

    private String country;

    private String CUIT;
    private String email;
    private BigInteger phone;
    private boolean state;
    private Date registerDate;
    private String remarks;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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
}
