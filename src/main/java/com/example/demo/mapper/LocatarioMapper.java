package com.example.demo.mapper;

import com.example.demo.dto.LocatarioDTO;
import com.example.demo.dto.LocatarioRequest;
import com.example.demo.model.Locatario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocatarioMapper {

    public LocatarioDTO toDTO(Locatario locatario) {
        if (locatario == null) {
            return null;
        }

        return new LocatarioDTO(
                locatario.getId(),
                locatario.getNome(),
                locatario.getCpf(),
                locatario.getTelefone(),
                locatario.getEmail()
        );
    }

    public List<LocatarioDTO> toDTOList(List<Locatario> locatarios) {
        return locatarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Locatario toEntity(LocatarioRequest request) {
        if (request == null) {
            return null;
        }

        Locatario locatario = new Locatario();
        locatario.setNome(request.nome());
        locatario.setCpf(request.cpf());
        locatario.setTelefone(request.telefone());
        locatario.setEmail(request.email());
        
        return locatario;
    }

    public void updateEntity(Locatario locatario, LocatarioRequest request) {
        if (request == null || locatario == null) {
            return;
        }

        locatario.setNome(request.nome());
        locatario.setCpf(request.cpf());
        locatario.setTelefone(request.telefone());
        locatario.setEmail(request.email());
    }
}
