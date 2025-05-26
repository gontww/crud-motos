package com.example.demo.controller;

import com.example.demo.model.CriarUsuarioRequest;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public Usuario criarUsuario(@RequestBody CriarUsuarioRequest usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @DeleteMapping("/excluir/{id}")
    public Usuario excluir(@PathVariable("id") Long id) {
        return usuarioService.deletarUsuario(id);
    }
}
