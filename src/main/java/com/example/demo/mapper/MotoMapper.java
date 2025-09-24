package com.example.demo.mapper;

import com.example.demo.dto.MotoDTO;
import com.example.demo.dto.MotoRequest;
import com.example.demo.model.Moto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MotoMapper {

    public MotoDTO toDTO(Moto moto) {
        if (moto == null) {
            return null;
        }

        return new MotoDTO(
                moto.getId(),
                moto.getPlaca(),
                moto.getModelo(),
                moto.getMarca(),
                moto.getAno(),
                moto.getCor(),
                moto.getStatus()
        );
    }

    public List<MotoDTO> toDTOList(List<Moto> motos) {
        return motos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Moto toEntity(MotoRequest request) {
        if (request == null) {
            return null;
        }

        Moto moto = new Moto();
        moto.setPlaca(request.placa());
        moto.setModelo(request.modelo());
        moto.setMarca(request.marca());
        moto.setAno(request.ano());
        moto.setCor(request.cor());
        moto.setStatus("disponivel");
        
        return moto;
    }

    public void updateEntity(Moto moto, MotoRequest request) {
        if (request == null || moto == null) {
            return;
        }

        moto.setPlaca(request.placa());
        moto.setModelo(request.modelo());
        moto.setMarca(request.marca());
        moto.setAno(request.ano());
        moto.setCor(request.cor());
    }
}
