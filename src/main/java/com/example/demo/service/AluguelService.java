package com.example.demo.service;

import com.example.demo.model.Aluguel;
import com.example.demo.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    public Aluguel findById(Long id) {
        return aluguelRepository.findById(id).orElseThrow();
    }

    public Aluguel save(Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }

    public Aluguel update(Long id, Aluguel aluguelAtualizado) {
        Aluguel aluguel = findById(id);
        aluguel.setDataInicio(aluguelAtualizado.getDataInicio());
        aluguel.setDataFim(aluguelAtualizado.getDataFim());
        aluguel.setMoto(aluguelAtualizado.getMoto());
        aluguel.setLocatario(aluguelAtualizado.getLocatario());
        return aluguelRepository.save(aluguel);
    }

    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }
}
