package com.example.demo.model;

import com.example.demo.enums.StatusAluguel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "moto_id")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "locatario_id")
    private Locatario locatario;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusAluguel status;
}
