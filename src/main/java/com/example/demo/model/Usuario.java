package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private boolean ativo;

    public Usuario(CriarUsuarioRequest criarUsuarioRequest) {
        this.nome = criarUsuarioRequest.getNome();
        this.email = criarUsuarioRequest.getEmail();
        this.senha = criarUsuarioRequest.getSenha();
        this.ativo = true;
    }

    public Usuario() {}
}
