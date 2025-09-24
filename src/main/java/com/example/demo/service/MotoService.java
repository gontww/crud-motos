package com.example.demo.service;

import com.example.demo.dto.MotoDTO;
import com.example.demo.dto.MotoRequest;
import com.example.demo.mapper.MotoMapper;
import com.example.demo.model.Moto;
import com.example.demo.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;
    @Autowired
    private MotoMapper motoMapper;

    public List<MotoDTO> findAll() {
        List<Moto> motos = motoRepository.findAll();
        return motoMapper.toDTOList(motos);
    }

    public List<MotoDTO> findAllDisponiveis() {
        List<Moto> motos = motoRepository.findAllDisponiveis();
        return motoMapper.toDTOList(motos);
    }

    public MotoDTO findById(Long id) {
        Moto moto = motoRepository.findById(id).orElseThrow();
        return motoMapper.toDTO(moto);
    }

    public Moto findEntityById(Long id) {
        return motoRepository.findById(id).orElseThrow();
    }

    public Moto saveEntity(Moto moto) {
        return motoRepository.save(moto);
    }

    public MotoDTO saveNova(MotoRequest request) {
        Moto moto = motoMapper.toEntity(request);
        moto = motoRepository.save(moto);
        return motoMapper.toDTO(moto);
    }

    public MotoDTO update(Long id, MotoRequest request) {
        Moto moto = findEntityById(id);
        motoMapper.updateEntity(moto, request);
        moto = motoRepository.save(moto);
        return motoMapper.toDTO(moto);
    }

    public void deleteById(Long id) {
        motoRepository.deleteById(id);
    }
}