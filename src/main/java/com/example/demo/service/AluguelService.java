package com.example.demo.service;

import com.example.demo.dto.AluguelDTO;
import com.example.demo.dto.AluguelRequest;
import com.example.demo.enums.StatusAluguel;
import com.example.demo.mapper.AluguelMapper;
import com.example.demo.model.Aluguel;
import com.example.demo.model.Locatario;
import com.example.demo.model.Moto;
import com.example.demo.repository.AluguelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private MotoService motoService;
    @Autowired
    private LocatarioService locatarioService;
    @Autowired
    private AluguelMapper aluguelMapper;

    public List<AluguelDTO> findAll() {
        List<Aluguel> alugueis = aluguelRepository.findAll();
        return aluguelMapper.toDTOList(alugueis);
    }

    public AluguelDTO findById(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow();
        return aluguelMapper.toDTO(aluguel);
    }

    // Método interno para buscar entidade (usado pelos outros métodos)
    private Aluguel findEntityById(Long id) {
        return aluguelRepository.findById(id).orElseThrow();
    }

    private boolean isMotoDisponivelNoPeriodo(Moto moto, LocalDate dataInicio, LocalDate dataFim) {
        if (!"DISPONIVEL".equals(moto.getStatus())) {
            return false;
        }

        List<Aluguel> alugueisExistentes = aluguelRepository.findByMotoId(moto.getId());
        
        return alugueisExistentes.stream().noneMatch(aluguel -> {
            // Verifica se tem sobreposição de datas
            return !(dataFim.isBefore(aluguel.getDataInicio()) || dataInicio.isAfter(aluguel.getDataFim())) &&
                    aluguel.getStatus().equals(StatusAluguel.ATIVO);
        });
    }

    public AluguelDTO save(AluguelRequest aluguelRequest) {
        Moto moto = motoService.findEntityById(aluguelRequest.motoId());
        Locatario locatario = locatarioService.findEntityById(aluguelRequest.locatarioId());

        if (!isMotoDisponivelNoPeriodo(moto, aluguelRequest.dataInicio(), aluguelRequest.dataFim())) {
            throw new RuntimeException("A moto não está disponível no período solicitado.");
        }

        moto.setStatus("INDISPONIVEL");
        moto = motoService.saveEntity(moto);

        Aluguel aluguel = aluguelMapper.toEntity(aluguelRequest, moto, locatario);
        aluguel = aluguelRepository.save(aluguel);
        return aluguelMapper.toDTO(aluguel);
    }

    public AluguelDTO update(Long id, AluguelRequest aluguelAtualizadoRequest) {
        Aluguel aluguel = findEntityById(id);
        Moto novaMoto = motoService.findEntityById(aluguelAtualizadoRequest.motoId());
        Locatario locatario = locatarioService.findEntityById(aluguelAtualizadoRequest.locatarioId());
        LocalDate dataInicio = aluguelAtualizadoRequest.dataInicio();
        LocalDate dataFim = aluguelAtualizadoRequest.dataFim();
        
        if (!aluguel.getDataInicio().equals(dataInicio) ||
            !aluguel.getDataFim().equals(dataFim)) {
            if (!isMotoDisponivelNoPeriodo(novaMoto, dataInicio, dataFim)) {
                throw new RuntimeException("A moto não está disponível no período solicitado.");
            }
        }
        if (!aluguel.getMoto().getId().equals(novaMoto.getId())) {
            if (!isMotoDisponivelNoPeriodo(novaMoto, dataInicio, dataFim)) {
                throw new RuntimeException("A nova moto não está disponível no período solicitado.");
            }

            Moto motoAnterior = aluguel.getMoto();
            motoAnterior.setStatus("DISPONIVEL");
            motoService.saveEntity(motoAnterior);

            novaMoto.setStatus("INDISPONIVEL");
            novaMoto = motoService.saveEntity(novaMoto);
        }

        aluguel.setDataInicio(dataInicio);
        aluguel.setDataFim(dataFim);
        aluguel.setMoto(novaMoto);
        aluguel.setLocatario(locatario);
        aluguel = aluguelRepository.save(aluguel);
        return aluguelMapper.toDTO(aluguel);
    }

    public void deleteById(Long id) {
        Aluguel aluguel = findEntityById(id);
        Moto moto = aluguel.getMoto();
        moto.setStatus("DISPONIVEL");
        motoService.saveEntity(moto);
        aluguelRepository.deleteById(id);
    }

    public AluguelDTO finalizar(Long id) {
        Aluguel aluguel = findEntityById(id);
        Moto moto = aluguel.getMoto();
        moto.setStatus("DISPONIVEL");
        motoService.saveEntity(moto);
        aluguel.setStatus(StatusAluguel.INATIVO);
        aluguel = aluguelRepository.save(aluguel);
        return aluguelMapper.toDTO(aluguel);
    }
}
