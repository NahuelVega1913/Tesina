package org.example.backendtesina.services;

import org.apache.catalina.User;
import org.example.backendtesina.DTOs.Post.postComment;
import org.example.backendtesina.entities.enums.TypeComment;
import org.example.backendtesina.entities.payment.CommentEntity;
import org.example.backendtesina.entities.payment.SpareEntity;
import org.example.backendtesina.entities.personal.NotificationEntity;
import org.example.backendtesina.entities.personal.UserEntity;
import org.example.backendtesina.jwt.JwtService;
import org.example.backendtesina.repositories.CommentRepository;
import org.example.backendtesina.repositories.SpareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommnetService {


    @Autowired
    CommentRepository repository;
    @Autowired
    JwtService jwtService;
    @Autowired
    SpareRepository spareRepository;
    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;

    public List<postComment> getAllComents(int id){
        SpareEntity spare = spareRepository.findById(id).get();
        List<CommentEntity> commentEntities =  spare.getComments();
        List<postComment> comments = new ArrayList<>();
        if(commentEntities != null) {
            for (CommentEntity  entity:commentEntities){
                postComment comment = new postComment();
                comment.setId(entity.getId());
                comment.setIdSpare(id);
                comment.setText(entity.getText());
                comment.setResponse(entity.getResponse());
                comments.add(comment);
            }
            return comments;
        }else return null;

    }
    public postComment postComment(postComment comment,String token){
        String email = jwtService.getEmailFromToken(token);


        SpareEntity spare  =spareRepository.findById(comment.getIdSpare()).get();
        if(spare.getComments().isEmpty()){
            spare.setComments( new ArrayList<>());
        }
        if(comment.getType().equals(TypeComment.COMMENT)){
            UserEntity user = userService.getEntity("admin@gmail.com");
            notificationService.createAdminComment(user,spare.getName());
            CommentEntity entity = new CommentEntity();
            entity.setFecha(comment.getFecha());
            entity.setEmailUser(email);
            entity.setResponse(null);
            entity.setSpare(spare);
            entity.setText(comment.getText());
            repository.save(entity);
        } else if (comment.getType().equals(TypeComment.RESPONSE)) {
           CommentEntity entity = repository.findById(comment.getId()).get();
            UserEntity userE = userService.getEntity(entity.getEmailUser());
            notificationService.createUserComment(userE,spare.getName());

            entity.setResponse(comment.getResponse());
           repository.save(entity);
        }

        return comment;
    }
}
