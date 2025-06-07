package com.example.demo.repository;

import com.example.demo.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    @Query(value = "select * from moto where status = 'DISPONIVEL'", nativeQuery = true)
    List<Moto> findAllDisponiveis();
}
