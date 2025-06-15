package com.example.demo.service;

import com.example.demo.enums.StatusAluguel;
import com.example.demo.model.Aluguel;
import com.example.demo.model.Moto;
import com.example.demo.repository.AluguelRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ScheduledTasksService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private MotoService motoService;

    // Executa todos os dias meia-noite
    @Scheduled(cron = "0 0 0 * * ?")
    @PostConstruct
    @Transactional
    public void verificarAlugueisVencidos() {
        log.info("Verificando alugueis vencidos");
        LocalDate hoje = LocalDate.now();
        List<Aluguel> alugueisVencidos = aluguelRepository.findByDataFimBeforeAndMotoStatus(hoje, "INDISPONIVEL");

        for (Aluguel aluguel : alugueisVencidos) {
            Moto moto = aluguel.getMoto();
            moto.setStatus("DISPONIVEL");
            aluguel.setStatus(StatusAluguel.INATIVO);
            aluguelRepository.save(aluguel);
            motoService.save(moto);
            log.info("Aluguel de id {} finalizado, moto diponivel: {}",aluguel.getId(), moto.getModelo());
        }
    }
} 