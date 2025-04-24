package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.GetSpareDTO;
import org.example.backendtesina.DTOs.PostProviderDTO;
import org.example.backendtesina.DTOs.PostSpareDTO;
import org.example.backendtesina.entities.SpareEntity;
import org.example.backendtesina.repositories.SpareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpareService {
    @Autowired
    SpareRepository repository;


    public List<GetSpareDTO> getAll(){
        return null;
    }
    public PostSpareDTO getSpare(int id){
        return null;
    }
    public PostSpareDTO postSpare(PostSpareDTO dto){
        // Implement the logic to save a new spare part
        return null;
    }
    public PostSpareDTO putSpare(PostSpareDTO dto){
        // Implement the logic to update an existing spare part
        return null;
    }

    public PostSpareDTO entityToDTO(PostSpareDTO dto){
        PostSpareDTO postSpareDTO = new PostSpareDTO();
//        postSpareDTO.setId(dto.getId());
//        postSpareDTO.setName(dto.getName());
//        postSpareDTO.setDescription(dto.getDescription());
//        postSpareDTO.setPrice(dto.getPrice());
//        postSpareDTO.setStock(dto.getStock());
        return postSpareDTO;
    }
    public SpareEntity dtoToEntity(PostSpareDTO dto) {
        SpareEntity spareEntity = new SpareEntity();
        return spareEntity;
    }
}
