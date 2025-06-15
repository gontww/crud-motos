package com.example.demo.service;

import com.example.demo.dto.AluguelRequest;
import com.example.demo.enums.StatusAluguel;
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

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    public Aluguel findById(Long id) {
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

    public Aluguel save(AluguelRequest aluguelRequest) {
        Moto moto = motoService.findById(aluguelRequest.motoId());
        Locatario locatario = locatarioService.findById(aluguelRequest.locatarioId());

        if (!isMotoDisponivelNoPeriodo(moto, aluguelRequest.dataInicio(), aluguelRequest.dataFim())) {
            throw new RuntimeException("A moto não está disponível no período solicitado.");
        }

        moto.setStatus("INDISPONIVEL");
        moto = motoService.save(moto);

        Aluguel aluguel = new Aluguel();
        aluguel.setDataInicio(aluguelRequest.dataInicio());
        aluguel.setDataFim(aluguelRequest.dataFim());
        aluguel.setMoto(moto);
        aluguel.setLocatario(locatario);
        aluguel.setStatus(StatusAluguel.ATIVO);
        return aluguelRepository.save(aluguel);
    }

    public Aluguel update(Long id, AluguelRequest aluguelAtualizadoRequest) {
        Aluguel aluguel = findById(id);
        Moto novaMoto = motoService.findById(aluguelAtualizadoRequest.motoId());
        Locatario locatario = locatarioService.findById(aluguelAtualizadoRequest.locatarioId());
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
            motoService.save(motoAnterior);

            novaMoto.setStatus("INDISPONIVEL");
            novaMoto = motoService.save(novaMoto);
        }

        aluguel.setDataInicio(dataInicio);
        aluguel.setDataFim(dataFim);
        aluguel.setMoto(novaMoto);
        aluguel.setLocatario(locatario);
        return aluguelRepository.save(aluguel);
    }

    public void deleteById(Long id) {
        Aluguel aluguel = findById(id);
        Moto moto = aluguel.getMoto();
        moto.setStatus("DISPONIVEL");
        motoService.save(moto);
        aluguelRepository.deleteById(id);
    }

    public Aluguel finalizar(Long id) {
        Aluguel aluguel = findById(id);
        Moto moto = aluguel.getMoto();
        moto.setStatus("DISPONIVEL");
        motoService.save(moto);
        aluguel.setStatus(StatusAluguel.INATIVO);
        return aluguelRepository.save(aluguel);
    }
}
