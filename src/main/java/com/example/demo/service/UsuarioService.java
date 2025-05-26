package com.example.demo.service;

import com.example.demo.model.CriarUsuarioRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(CriarUsuarioRequest usuario){
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            throw new RuntimeException("é necessario um email");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()){
            throw new RuntimeException("é necessario um nome");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()){
            throw new RuntimeException("é necessario uma senha");
        }

        Usuario usuarioSalvo = new Usuario(usuario);
        return usuarioRepository.save(usuarioSalvo);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario deletarUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new EntityNotFoundException("Usuario não existe");
        }

        usuarioRepository.delete(usuario.get());
        return usuario.get();
    }
}
