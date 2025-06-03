package com.example.demo.service;

import com.example.demo.model.Moto;
import com.example.demo.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    public Moto findById(Long id) {
        return motoRepository.findById(id).orElseThrow();
    }

    public Moto save(Moto moto) {
        moto.setStatus("DISPONIVEL");
        return motoRepository.save(moto);
    }

    public Moto update(Long id, Moto motoAtualizado) {
        Moto moto = findById(id);
        moto.setPlaca(motoAtualizado.getPlaca());
        moto.setModelo(motoAtualizado.getModelo());
        moto.setMarca(motoAtualizado.getMarca());
        moto.setAno(motoAtualizado.getAno());
        moto.setCor(motoAtualizado.getCor());
        moto.setStatus(motoAtualizado.getStatus());
        return motoRepository.save(moto);
    }

    public void deleteById(Long id) {
        motoRepository.deleteById(id);
    }
}