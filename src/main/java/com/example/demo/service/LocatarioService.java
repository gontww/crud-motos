package com.example.demo.service;

import com.example.demo.model.Locatario;
import com.example.demo.repository.LocatarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LocatarioService {

    @Autowired
    private LocatarioRepository locatarioRepository;

    public List<Locatario> findAll() {
        return locatarioRepository.findAll();
    }

    public Locatario findById(Long id) {
        return locatarioRepository.findById(id).orElseThrow();
    }

    public Locatario save(Locatario locatario) {
        if (locatarioRepository.existsByCpf(locatario.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        return locatarioRepository.save(locatario);
    }

    public Locatario update(Long id, Locatario locatarioAtualizado) {
        Locatario locatario = findById(id);

        locatario.setNome(locatarioAtualizado.getNome());
        locatario.setCpf(locatarioAtualizado.getCpf());
        locatario.setEmail(locatarioAtualizado.getEmail());
        locatario.setTelefone(locatarioAtualizado.getTelefone());

        return locatarioRepository.save(locatario);
    }

    public void deleteById(Long id) {
        locatarioRepository.deleteById(id);
    }
}