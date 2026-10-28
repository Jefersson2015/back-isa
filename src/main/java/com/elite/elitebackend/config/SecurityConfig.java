package com.elite.elitebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desactivar CSRF (obligatorio para APIs REST con Postman/Frontend)
                .csrf(csrf -> csrf.disable())

                // 2. Configurar CORS correctamente
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 3. Permitir acceso PÚBLICO a todas las rutas de la API temporalmente
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuarios/**").permitAll()
                        .requestMatchers("/api/productos/**").permitAll()
                        .requestMatchers("/api/categorias/**").permitAll()
                        .requestMatchers("/api/cliente/**").permitAll()
                        .requestMatchers("/api/pedidos/**").permitAll()
                        .requestMatchers("/api/carrito/**").permitAll()
                        .requestMatchers("/api/carrito_detalle/**").permitAll()
                        .anyRequest().permitAll() // <-- Permite todo para descartar que sea seguridad
                )

                // 4. No usar sesiones HTTP (Stateless para JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        // NOTA: Hemos quitado el .addFilterBefore del JWT temporalmente
        // para asegurar que el login pase sin bloqueos.

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // IMPORTANTE: NO usar "*" aquí porque allowCredentials es true
        configuration.setAllowedOrigins(Arrays.asList(
                "https://fron-isa.vercel.app",
                "http://localhost:3000"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Permitir que el frontend envíe el token JWT
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}