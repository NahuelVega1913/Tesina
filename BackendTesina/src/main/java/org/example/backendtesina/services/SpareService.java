package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetSpareDTO;
import org.example.backendtesina.DTOs.Post.PostSpareDTO;
import org.example.backendtesina.entities.personal.NotificationEntity;
import org.example.backendtesina.entities.personal.ProviderEntity;
import org.example.backendtesina.entities.payment.SpareEntity;
import org.example.backendtesina.entities.enums.CategorySpareEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.repositories.ProviderRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpareService {
    @Autowired
    SpareRepository repository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserService userService;

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
            if(spare.getProvider() !=null){
                dto.setProvider(spare.getProvider().getId());
            }
            dto.setId(spare.getId());
            dto.setBrand(spare.getBrand());
            dto.setName(spare.getName());
            dto.setStars(spare.getStars());
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
    public PostSpareDTO postSpare(String name,int providerId ,double price, int discaunt, int stock, String brand,Double stars, String category,
                                  String description, MultipartFile image1, MultipartFile image2,
                                  MultipartFile image3, MultipartFile image4, MultipartFile image5) throws IOException
    {
        ProviderEntity provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        SpareEntity spare = new SpareEntity();
        spare.setProvider(provider);
        spare.setName(name);
        spare.setPrice(price);
        spare.setStars(stars);
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

    public PostSpareDTO putSpare(int id, String name, int providerId, double price, int discaunt, int stock,
                                 String brand, Double stars, String category, String description,
                                 MultipartFile image1, MultipartFile image2, MultipartFile image3,
                                 MultipartFile image4, MultipartFile image5) throws IOException {

        SpareEntity spare = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spare not found with id: " + id));

        // Eliminar imágenes anteriores
        List<String> imagenesActuales = Arrays.asList(spare.getImage1(), spare.getImage2(), spare.getImage3(), spare.getImage4(), spare.getImage5());
        for (String imagen : imagenesActuales) {
            if (imagen != null) {
                eliminarImagen(imagen);
            }
        }

        // Actualizar campos
        ProviderEntity provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found with id: " + providerId));

        spare.setProvider(provider);
        spare.setName(name);
        spare.setPrice(price);
        spare.setDiscaunt(discaunt);
        spare.setStock(stock);
        if(spare.getStock() <= 5){
            UserEntity user = userService.getEntity("admin@gmail.com");
            notificationService.pocoStock(user,spare.getName() +" "+ spare.getBrand());
        }
        spare.setBrand(brand);
        spare.setStars(stars);
        spare.setCategory(CategorySpareEntity.valueOf(category));
        spare.setDescription(description);

        // Guardar nuevas imágenes
        spare.setImage1(image1 != null ? saveImage(image1) : null);
        spare.setImage2(image2 != null ? saveImage(image2) : null);
        spare.setImage3(image3 != null ? saveImage(image3) : null);
        spare.setImage4(image4 != null ? saveImage(image4) : null);
        spare.setImage5(image5 != null ? saveImage(image5) : null);

        repository.save(spare);

        return entityToDTO(spare);
    }
    public PostSpareDTO putSpare(PostSpareDTO dto){
        // Implement the logic to update an existing spare part
        SpareEntity spare = repository.findById(dto.getId()).orElse(null);
        List<String> imagenesActuales = Arrays.asList(spare.getImage1(), spare.getImage2(), spare.getImage3(),spare.getImage4(),spare.getImage5());
        List<String> nuevasImagenes = Arrays.asList(dto.getImage1(), dto.getImage2(),dto.getImage3(),dto.getImage4(),dto.getImage5());

        spare.setName(dto.getName());
        spare.setPrice(dto.getPrice());
        spare.setStars(dto.getStars());
        spare.setDiscaunt(dto.getDiscaunt());
        spare.setStock(dto.getStock());

        spare.setBrand(dto.getBrand());
        spare.setCategory(CategorySpareEntity.valueOf(dto.getCategory()));
        spare.setDescription(dto.getDescription());
        List<String> imagenesAEliminar = imagenesActuales.stream()
                .filter(Objects::nonNull)
                .filter(imagen -> !nuevasImagenes.contains(imagen))
                .collect(Collectors.toList());
        for (String imagen : imagenesAEliminar) {
            eliminarImagen(imagen);
        }

        return null;
    }
    public void eliminarImagen(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                // Extraer el nombre del archivo sin usar URI (mejor)
                String fileName = imagePath.substring(imagePath.lastIndexOf('/') + 1);

                Path path = Paths.get(uploadDir, fileName);
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        return "http://localhost:8080/uploads/" + fileName;
    }
}
