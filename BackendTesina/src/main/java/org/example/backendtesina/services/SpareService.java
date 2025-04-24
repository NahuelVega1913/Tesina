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
        List<SpareEntity> lst = repository.findAll();
        List<GetSpareDTO> response = new java.util.ArrayList<>();
        for (SpareEntity spare : lst) {
            GetSpareDTO dto = new GetSpareDTO();
            dto.setId(spare.getId());
            dto.setBrand(spare.getBrand());
            dto.setName(spare.getName());
            dto.setDescription(spare.getDescription());
            dto.setPrice(spare.getPrice());
            dto.setDiscaunt(spare.getDiscaunt());
            dto.setCategory(spare.getCategory().toString());
            dto.setImage1(spare.getImage1());
            dto.setStock(spare.getStock());
            response.add(dto);
        }
        return response;
    }
    public PostSpareDTO getSpare(int id){
        SpareEntity spare = repository.findById(id).orElse(null);
        if(spare != null){
            PostSpareDTO dto = new PostSpareDTO();
            dto.setId(spare.getId());
            dto.setBrand(spare.getBrand());
            dto.setName(spare.getName());
            dto.setStock(spare.getStock());
            dto.setActive(spare.isActive());
            dto.setDescription(spare.getDescription());
            dto.setPrice(spare.getPrice());
            dto.setDiscaunt(spare.getDiscaunt());
            dto.setCategory(spare.getCategory().toString());
            List<String> images = new java.util.ArrayList<>();
            if(spare.getImage1()  != null){
                images.add(spare.getImage1());
            }
            if(spare.getImage2()  != null){
                images.add(spare.getImage2());
            }
            if(spare.getImage3()  != null){
                images.add(spare.getImage3());
            }
            if(spare.getImage4()  != null){
                images.add(spare.getImage4());
            }
            if(spare.getImage5()  != null){
                images.add(spare.getImage5());
            }
            dto.setUrlImages(images);

            return dto;
        }

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

    public PostSpareDTO entityToDTO(SpareEntity dto){
        PostSpareDTO postSpareDTO = new PostSpareDTO();
        postSpareDTO.setId(dto.getId());
       postSpareDTO.setName(dto.getName());
       postSpareDTO.setDescription(dto.getDescription());
        postSpareDTO.setPrice(dto.getPrice());
        postSpareDTO.setStock(dto.getStock());
        return postSpareDTO;
    }
    public SpareEntity dtoToEntity(PostSpareDTO dto) {
        SpareEntity spareEntity = new SpareEntity();
        return spareEntity;
    }
}
