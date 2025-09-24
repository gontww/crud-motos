package com.example.demo.service;

import com.example.demo.dto.LocatarioDTO;
import com.example.demo.dto.LocatarioRequest;
import com.example.demo.mapper.LocatarioMapper;
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
    @Autowired
    private LocatarioMapper locatarioMapper;

    public List<LocatarioDTO> findAll() {
        List<Locatario> locatarios = locatarioRepository.findAll();
        return locatarioMapper.toDTOList(locatarios);
    }

    public LocatarioDTO findById(Long id) {
        Locatario locatario = locatarioRepository.findById(id).orElseThrow();
        return locatarioMapper.toDTO(locatario);
    }

    public Locatario findEntityById(Long id) {
        return locatarioRepository.findById(id).orElseThrow();
    }

    public LocatarioDTO save(LocatarioRequest request) {
        if (locatarioRepository.existsByCpf(request.cpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        Locatario locatario = locatarioMapper.toEntity(request);
        locatario = locatarioRepository.save(locatario);
        return locatarioMapper.toDTO(locatario);
    }

    public LocatarioDTO update(Long id, LocatarioRequest request) {
        Locatario locatario = findEntityById(id);
        locatarioMapper.updateEntity(locatario, request);
        locatario = locatarioRepository.save(locatario);
        return locatarioMapper.toDTO(locatario);
    }

    public void deleteById(Long id) {
        locatarioRepository.deleteById(id);
    }
}