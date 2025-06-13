package com.example.demo.dto;

import lombok.Data;

@Data
public class RelatorioAluguelDTO {
    private String modelo;
    private String marca;
    private String placa;
    private long totalAlugueis;

    public RelatorioAluguelDTO() {
    }

    public RelatorioAluguelDTO(String modelo, String marca, String placa, long totalAlugueis) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.totalAlugueis = totalAlugueis;
    }
} 