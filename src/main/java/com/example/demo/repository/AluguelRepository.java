package com.example.demo.repository;

import com.example.demo.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByMotoId(Long motoId);
    List<Aluguel> findByDataFimBeforeAndMotoStatus(LocalDate data, String status);
}