package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @ManyToOne(optional = false)
    @JoinColumn(name = "moto_id")
    private Moto moto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locatario_id")
    private Locatario locatario;
}
