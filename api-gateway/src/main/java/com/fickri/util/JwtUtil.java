package com.fickri.util;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    //public static final String SECRET = "24324354654576834299855565535353223454545626262677899991123431244";

    private final String jwtSecret;

    public JwtUtil(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret); 
        return Keys.hmacShaKeyFor(keyBytes);
    } 

    private Claims extractAllClaims (String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String validateToken(String token) {
        final Claims claims = extractAllClaims(token);
        return "Tokend is Valid";
    } 
}
