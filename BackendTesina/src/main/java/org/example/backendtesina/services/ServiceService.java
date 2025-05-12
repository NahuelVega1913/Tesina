package org.example.backendtesina.services;

import org.example.backendtesina.repositories.SeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;

@Service
public class ServiceService {

    @Autowired
    SeviceRepository repository;



    public void registerInspection(){

    }
}
