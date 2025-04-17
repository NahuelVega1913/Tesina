package org.example.backendtesina.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

import static javax.crypto.Cipher.SECRET_KEY;


@Service
public class JwtService {

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(),user);
    }

    public String getToken(Map<String, Objects> extraClaims, UserDetails user){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact() ;
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode("MECHANIC_KEY");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
