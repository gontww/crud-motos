package com.example.demo.dto;

import com.example.demo.enums.StatusAluguel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AluguelDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAluguel status;
    private MotoDTO moto;
    private LocatarioDTO locatario;

    public AluguelDTO() {}

    public AluguelDTO(Long id, LocalDate dataInicio, LocalDate dataFim, StatusAluguel status, MotoDTO moto, LocatarioDTO locatario) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.moto = moto;
        this.locatario = locatario;
    }
}
