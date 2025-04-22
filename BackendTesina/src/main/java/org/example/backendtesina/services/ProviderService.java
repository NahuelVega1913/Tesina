package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.GetProvidersDTO;
import org.example.backendtesina.DTOs.ProviderDTO;
import org.example.backendtesina.DTOs.RegisterDto;
import org.example.backendtesina.entities.ProviderEntity;
import org.example.backendtesina.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProviderService {


    @Autowired
    ProviderRepository repository;

    public ProviderDTO getProvider(Integer id){
        return null;
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

    public RegisterDto putProvider(RegisterDto dto){
        return null;
    }

    public RegisterDto registerProvider(RegisterDto dto){
        return null;
    }
}
