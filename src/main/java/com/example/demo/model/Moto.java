package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "moto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String placa;

    @Column
    private String modelo;

    @Column
    private String marca;

    @Column
    private Integer ano;

    @Column
    private String cor;

    @Column
    private String status;

    public Moto() {}
}
