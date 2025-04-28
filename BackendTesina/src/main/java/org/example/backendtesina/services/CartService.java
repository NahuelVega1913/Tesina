package org.example.backendtesina.services;

import org.example.backendtesina.DTOs.Get.GetCartDTO;
import org.example.backendtesina.DTOs.Get.GetSpareDTO;
import org.example.backendtesina.entities.CartEntity;
import org.example.backendtesina.entities.SpareEntity;
import org.example.backendtesina.entities.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.CartRepository;
import org.example.backendtesina.repositories.SpareRepository;
import org.example.backendtesina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    SpareRepository spareRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;


    public CartEntity postCart(String token, int idSpare) {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        SpareEntity spare = spareRepository.findById(idSpare).orElse(null);
        if(user.getCart() == null){
            CartEntity cart = new CartEntity();
            cart.setUser(user);
            user.setCart(cart);
        }
        CartEntity cart = user.getCart();
        List<SpareEntity> spares = new ArrayList<>();
        if(cart.getSpares() == null){
            cart.setSpares(spares);
        }
        if(spare != null){
            cart.getSpares().add(spare);
        }
        cartRepository.save(cart);


        return cart;
    }

    public GetCartDTO getCart(String token) {
        String email = jwtService.getEmailFromToken(token);
        UserEntity user = userRepository.findByEmail(email).orElse(null);


        CartEntity cart = user.getCart();

        GetCartDTO dto = new GetCartDTO();
        dto.setId(cart.getId());
        dto.setUser(user.getName());
        List<SpareEntity> spareList = cart.getSpares();
        List<GetSpareDTO> spareDTOList = new ArrayList<>();
        for (SpareEntity spare : spareList) {
            GetSpareDTO spareDTO = new GetSpareDTO();
            spareDTO.setBrand(spare.getBrand());
            spareDTO.setName(spare.getName());
            spareDTO.setDescription(spare.getDescription());
            spareDTO.setPrice(spare.getPrice());
            spareDTO.setDiscaunt(spare.getDiscaunt());
            spareDTO.setImage1(spare.getImage1());
            spareDTO.setStock(spare.getStock());
            spareDTOList.add(spareDTO);
        }
        dto.setSpareList(spareDTOList);
        return dto;
    }





}
