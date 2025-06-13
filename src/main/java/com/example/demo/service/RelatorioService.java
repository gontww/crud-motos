package com.example.demo.service;

import com.example.demo.dto.RelatorioAluguelDTO;
import com.example.demo.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<RelatorioAluguelDTO> gerarRelatorioAlugueis(LocalDate startDate, LocalDate endDate) {
        return aluguelRepository.findByDataInicioBetween(startDate, endDate)
                .stream()
                .collect(Collectors.groupingBy(
                        aluguel -> aluguel.getMoto(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                alugueis -> new RelatorioAluguelDTO(
                                        alugueis.get(0).getMoto().getModelo(),
                                        alugueis.get(0).getMoto().getMarca(),
                                        alugueis.get(0).getMoto().getPlaca(),
                                        alugueis.size()
                                )
                        )
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}