package com.elite.elitebackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Clave secreta para firmar los tokens (debe ser larga y segura)
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    // Generar un token para un usuario
    public String generarToken(String username, String rol) {
        return Jwts.builder()
                .subject(username)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SECRET_KEY)
                .compact();
    }

    // Validar y extraer información del token
    public Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extraer el username del token
    public String extraerUsername(String token) {
        return validarToken(token).getSubject();
    }

    // Extraer el rol del token
    public String extraerRol(String token) {
        return validarToken(token).get("rol", String.class);
    }
}