package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.GetProvidersDTO;
import org.example.backendtesina.DTOs.PostProviderDTO;
import org.example.backendtesina.DTOs.ProviderDTO;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.ProviderEntity;
import org.example.backendtesina.entities.enums.CityEntity;
import org.example.backendtesina.entities.enums.CountryEntity;
import org.example.backendtesina.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {


    @Autowired
    ProviderRepository repository;

    public PostProviderDTO getProvider(Integer id){
        Optional<ProviderEntity> p =repository.findById(id);
        PostProviderDTO provider = new PostProviderDTO();
        if(p == null){
            return null;
        }
        provider.setCategory(p.get().getCategory());
        provider.setCity(p.get().getCity().toString());
        provider.setCountry(p.get().getCountry().toString());
        provider.setId(p.get().getId());
        provider.setName(p.get().getName());
        provider.setPhone(p.get().getPhone());
        provider.setRemarks(p.get().getRemarks());
        provider.setCUIT(p.get().getCUIT());
        provider.setAdress(p.get().getAdress());
        provider.setEmail(p.get().getEmail());
        provider.setRegisterDate(p.get().getRegisterDate());

        return provider;
    }

    public ProviderEntity dtoToEntity(PostProviderDTO dto){
        ProviderEntity entity = new ProviderEntity();
        entity.setCategory(dto.getCategory());
        entity.setCity(dto.getCity());
        entity.setCountry(CountryEntity.valueOf(dto.getCountry()));
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setRemarks(dto.getRemarks());
        entity.setCUIT(dto.getCUIT());
        entity.setAdress(dto.getAdress());
        entity.setEmail(dto.getEmail());
        entity.setRegisterDate(dto.getRegisterDate());
        return entity;
    }
    public PostProviderDTO EntityToDto(ProviderEntity entity){
        PostProviderDTO dto = new PostProviderDTO();
        dto.setCategory(entity.getCategory());
        dto.setCity(entity.getCity().toString());
        dto.setCountry(entity.getCountry().toString());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setRemarks(entity.getRemarks());
        dto.setCUIT(entity.getCUIT());
        dto.setAdress(entity.getAdress());
        dto.setEmail(entity.getEmail());
        dto.setRegisterDate(entity.getRegisterDate());
        return dto;
    }

    public List<GetProvidersDTO> getAll(){
        List<ProviderEntity> lstProviders = repository.findAll();
        List<GetProvidersDTO> lstDtoProviders = new ArrayList<>();
        if(lstProviders.isEmpty()){
            return null;
        }
        for (ProviderEntity p:lstProviders){
            GetProvidersDTO provider = new GetProvidersDTO();
            provider.setCategory(p.getCategory());
            provider.setCity(p.getCity());
            provider.setCountry(p.getCountry());
            provider.setId(p.getId());
            provider.setName(p.getName());
            provider.setPhone(p.getPhone());
            lstDtoProviders.add(provider);
        }
        return lstDtoProviders;
    }

    public PostProviderDTO putProvider(PostProviderDTO dto){
        Optional<ProviderEntity> p =repository.findById(dto.getId());
        if(p.isPresent()){
            ProviderEntity entity = p.get();
            entity.setCategory(dto.getCategory());
            entity.setCity(dto.getCity());
            entity.setCountry(CountryEntity.valueOf(dto.getCountry()));
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setPhone(dto.getPhone());
            entity.setRemarks(dto.getRemarks());
            entity.setCUIT(dto.getCUIT());
            entity.setAdress(dto.getAdress());
            entity.setEmail(dto.getEmail());
            entity.setRegisterDate(dto.getRegisterDate());
            repository.save(entity);
            return dto;
        }

        return null;
    }

    public PostProviderDTO registerProvider(PostProviderDTO dto){
        ProviderEntity entity = this.dtoToEntity(dto);
        repository.save(entity);
        return dto;

    }
}
