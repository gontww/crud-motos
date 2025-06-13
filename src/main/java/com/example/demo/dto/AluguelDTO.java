package com.example.demo.dto;

import com.example.demo.enums.StatusAluguel;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AluguelDTO {
    private Long id;
    private Long motoId;
    private Long locatarioId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAluguel status;
} 