package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_STRING = "e40bAQ6kWwNCDg7ATi8o5z5nLMJRm5EPrzPx1fYLLiscB4SR8eiWzFErl4+9Ktld3DVWY2CbvCcgA85UuGcjpWEqjHpQ8mWgF5YnGNvUaAM=";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));


    public String generateToken(String username) {
        // 10 dias
        long EXPIRATION = 864_000_000;
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            System.err.println("Erro ao validar token JWT: " + ex.getMessage());
            return false;
        }
    }
}
