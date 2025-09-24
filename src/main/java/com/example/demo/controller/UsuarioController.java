package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioUpdateDTO;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> buscarPerfil(Authentication authentication) {
        return ResponseEntity.ok(usuarioService.buscarPerfilPorLogin(authentication.getPrincipal().toString()));
    }

    @PutMapping("/perfil")
    public ResponseEntity<UsuarioDTO> atualizarPerfil(Authentication authentication, @RequestBody UsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizarPerfil(authentication.getPrincipal().toString(), dto));
    }
} 