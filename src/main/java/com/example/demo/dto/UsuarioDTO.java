package com.example.demo.dto;

import com.example.demo.enums.UsuarioRole;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String login;
    private UsuarioRole role;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String login, UsuarioRole role) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.role = role;
    }
}
