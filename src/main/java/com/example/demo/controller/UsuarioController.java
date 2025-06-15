package com.example.demo.controller;

import com.example.demo.dto.UsuarioUpdateDTO;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<UserDetails> buscarPerfil(Authentication authentication) {
        return ResponseEntity.ok(usuarioService.loadUserByUsername(authentication.getPrincipal().toString()));
    }

    @PutMapping("/perfil")
    public ResponseEntity<Usuario> atualizarPerfil(Authentication authentication, @RequestBody UsuarioUpdateDTO dto) {
        UserDetails usuario = usuarioService.loadUserByUsername(authentication.getPrincipal().toString());
        return ResponseEntity.ok(usuarioService.atualizarPerfil(usuario.getUsername(), dto));
    }
} 