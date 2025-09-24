package com.example.demo.service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioUpdateDTO;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public UsuarioDTO atualizarPerfil(String login, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioMapper.updateEntity(usuario, dto);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO buscarPerfilPorLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.toDTO(usuario);
    }
} 