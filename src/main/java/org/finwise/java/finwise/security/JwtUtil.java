package org.finwise.java.finwise.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.hmacShaKeyFor("secret_key_sicura_deveessserepiulungaaaaaaaaaaaaaaa_Aaaaaaaaaaaaaaaaaaa_123456789012345678901234".getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 ora

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    //aggiunte 
    public String extractUsername(String token) {
    return Jwts.parserBuilder()
               .setSigningKey(SECRET_KEY)
               .build()
               .parseClaimsJws(token)
               .getBody()
               .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(SECRET_KEY)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            return claims.getSubject().equals(userDetails.getUsername())
                && !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
}
