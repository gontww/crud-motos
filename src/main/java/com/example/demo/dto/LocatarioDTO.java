package com.example.demo.dto;

import lombok.Data;

@Data
public class LocatarioDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public LocatarioDTO() {}

    public LocatarioDTO(Long id, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }
}
