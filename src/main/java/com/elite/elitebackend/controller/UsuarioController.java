package com.elite.elitebackend.controller;

import com.elite.elitebackend.model.Cliente;
import com.elite.elitebackend.model.Usuario;
import com.elite.elitebackend.model.UsuarioResponse;
import com.elite.elitebackend.repository.ClienteRepository;
import com.elite.elitebackend.repository.UsuarioRepository;
import com.elite.elitebackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        // Crear cliente asociado
        Cliente cliente = new Cliente();
        cliente.setCorreo(usuario.getUsername());
        cliente.setNombre("Cliente Registrado");
        cliente.setTelefono("Sin especificar");
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Vincular cliente al usuario
        usuario.setCliente(clienteGuardado);

        // Encriptar contraseña y guardar
        String passwordPlano = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(passwordPlano));

        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario credenciales) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(credenciales.getUsername());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            boolean esValida = passwordEncoder.matches(credenciales.getPassword(), usuario.getPassword());

            if (esValida) {
                // Generar token JWT
                String token = jwtUtil.generarToken(usuario.getUsername(), usuario.getRoll());

                // Crear respuesta con token
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("token", token);
                respuesta.put("usuario", new UsuarioResponse(usuario));

                return ResponseEntity.ok(respuesta);
            }
        }

        return ResponseEntity.status(401).body(Map.of("mensaje", "Error en la autenticación"));
    }

    @GetMapping
    public java.util.List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}