package com.example.demo.repository;

import com.example.demo.model.Locatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
    boolean existsByCpf(String cpf);
}
