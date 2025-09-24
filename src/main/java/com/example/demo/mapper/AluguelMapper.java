package com.example.demo.mapper;

import com.example.demo.dto.AluguelDTO;
import com.example.demo.dto.AluguelRequest;
import com.example.demo.model.Aluguel;
import com.example.demo.model.Locatario;
import com.example.demo.model.Moto;
import com.example.demo.enums.StatusAluguel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AluguelMapper {

    private final MotoMapper motoMapper;
    private final LocatarioMapper locatarioMapper;

    public AluguelMapper(MotoMapper motoMapper, LocatarioMapper locatarioMapper) {
        this.motoMapper = motoMapper;
        this.locatarioMapper = locatarioMapper;
    }

    public AluguelDTO toDTO(Aluguel aluguel) {
        if (aluguel == null) {
            return null;
        }

        return new AluguelDTO(
                aluguel.getId(),
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.getStatus(),
                motoMapper.toDTO(aluguel.getMoto()),
                locatarioMapper.toDTO(aluguel.getLocatario())
        );
    }

    public List<AluguelDTO> toDTOList(List<Aluguel> alugueis) {
        return alugueis.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Aluguel toEntity(AluguelRequest request, Moto moto, Locatario locatario) {
        if (request == null) {
            return null;
        }

        Aluguel aluguel = new Aluguel();
        aluguel.setDataInicio(request.dataInicio());
        aluguel.setDataFim(request.dataFim());
        aluguel.setMoto(moto);
        aluguel.setLocatario(locatario);
        aluguel.setStatus(StatusAluguel.ATIVO);
        
        return aluguel;
    }
}
