package org.example.backendtesina.DTOs.Get;

import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.enums.CityEntity;
import org.example.backendtesina.entities.enums.CountryEntity;

import java.math.BigInteger;

public class GetProvidersDTO {

    private int id;
    private String name;
    private CountryEntity country;
    private String city;
    private Boolean state;
    private CategorySpareEntity category;
    private BigInteger phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CategorySpareEntity getCategory() {
        return category;
    }

    public void setCategory(CategorySpareEntity category) {
        this.category = category;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }


    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
