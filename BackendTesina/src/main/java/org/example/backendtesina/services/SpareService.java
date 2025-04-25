package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.GetSpareDTO;
import org.example.backendtesina.DTOs.PostProviderDTO;
import org.example.backendtesina.DTOs.PostSpareDTO;
import org.example.backendtesina.entities.SpareEntity;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.repositories.SpareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class SpareService {
    @Autowired
    SpareRepository repository;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

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
    public PostSpareDTO postSpare(String name, double price, int discaunt, int stock, String brand, String category,
                                  String description, MultipartFile image1, MultipartFile image2,
                                  MultipartFile image3, MultipartFile image4, MultipartFile image5) throws IOException
    {
        SpareEntity spare = new SpareEntity();
        spare.setName(name);
        spare.setPrice(price);
        spare.setDiscaunt(discaunt);
        spare.setStock(stock);
        spare.setBrand(brand);
        spare.setCategory(CategorySpareEntity.valueOf(category));
        spare.setDescription(description);

        spare.setImage1(saveImage(image1));
        spare.setImage2(image2 != null ? saveImage(image2) : null);
        spare.setImage3(image3 != null ? saveImage(image3) : null);
        spare.setImage4(image4 != null ? saveImage(image4) : null);
        spare.setImage5(image5 != null ? saveImage(image5) : null);

        repository.save(spare);
        return entityToDTO(spare);
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
    private String saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + fileName;
    }
}
