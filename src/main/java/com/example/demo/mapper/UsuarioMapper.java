package com.example.demo.mapper;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.dto.UsuarioUpdateDTO;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getRole()
        );
    }

    public void updateEntity(Usuario usuario, UsuarioUpdateDTO dto) {
        if (dto == null || usuario == null) {
            return;
        }

        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }
        if (dto.getLogin() != null) {
            usuario.setLogin(dto.getLogin());
        }
        if (dto.getSenha() != null) {
            usuario.setSenha(dto.getSenha());
        }
    }
}
