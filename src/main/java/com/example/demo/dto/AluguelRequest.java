package com.example.demo.dto;

import java.time.LocalDate;

public record AluguelRequest(LocalDate dataInicio, LocalDate dataFim, Long motoId, Long locatarioId) {}
