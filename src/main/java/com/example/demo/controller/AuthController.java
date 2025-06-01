package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByLogin(loginRequest.login()).orElseThrow(EntityNotFoundException::new);
        if (!usuario.getPassword().equals(loginRequest.senha())) {
            throw new RuntimeException("Senha incorreta");
        }
        return new ResponseEntity<>(jwtUtil.generateToken(usuario.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/teste-token")
    public String testeToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Autenticado como: " + auth.getName() + " | Roles: " + auth.getAuthorities();
    }
}