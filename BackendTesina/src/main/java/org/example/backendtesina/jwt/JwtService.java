package org.example.backendtesina.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;


@Service
public class JwtService {
    private static final String secret_key = "yM2Lk5tN7C3fVz9Wq6RqZ8rH4k0pXa2ZbK8nGp+H4Wg=";

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(),user);
    }

    public String getToken(Map<String, Object> extraClaims, UserDetails user){
        System.out.println("Username del user (para el sub): " + user.getUsername());

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact() ;
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getEmailFromToken(String token) {
        try {
            return getClaim(token, Claims::getSubject);
        } catch (Exception e) {
            System.out.println("Error extrayendo email del token: " + e.getMessage());
            // O usa un logger adecuado
            return null;
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String mail = getEmailFromToken(token);
        return (mail.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Error parseando el token: " + e.getMessage());
            // O usa un logger adecuado
            throw e; // o maneja la excepción según tu lógica
        }
    }
    public  <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration (String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
