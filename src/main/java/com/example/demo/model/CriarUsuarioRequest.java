package com.example.demo.model;

import lombok.Data;

@Data
public class CriarUsuarioRequest {
    private String nome;

    private String email;

    private String senha;
}
