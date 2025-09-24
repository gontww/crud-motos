package com.example.demo.dto;

import lombok.Data;

@Data
public class MotoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Integer ano;
    private String cor;
    private String status;

    public MotoDTO() {}

    public MotoDTO(Long id, String placa, String modelo, String marca, Integer ano, String cor, String status) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.status = status;
    }
}
