package com.example.demo.service;

import com.example.demo.dto.RelatorioAluguelDTO;
import com.example.demo.model.Aluguel;
import com.example.demo.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<RelatorioAluguelDTO> gerarRelatorioAlugueis(LocalDate startDate, LocalDate endDate) {
        return new ArrayList<>(aluguelRepository.findByDataInicioBetween(startDate, endDate)
                .stream()
                .collect(Collectors.groupingBy(
                        Aluguel::getMoto,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                alugueis -> new RelatorioAluguelDTO(
                                        alugueis.getFirst().getMoto().getModelo(),
                                        alugueis.getFirst().getMoto().getMarca(),
                                        alugueis.getFirst().getMoto().getPlaca(),
                                        alugueis.size()
                                )
                        )
                ))
                .values());
    }
}