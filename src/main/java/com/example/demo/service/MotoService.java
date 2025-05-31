package com.example.demo.service;

import com.example.demo.model.Moto;
import com.example.demo.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public Moto criarMoto(Moto moto){
//        if (moto.getEmail() == null || moto.getEmail().isEmpty()){
//            throw new RuntimeException("é necessario um email");
//        }
//        if (moto.getNome() == null || moto.getNome().isEmpty()){
//            throw new RuntimeException("é necessario um nome");
//        }
//        if (moto.getSenha() == null || moto.getSenha().isEmpty()){
//            throw new RuntimeException("é necessario uma senha");
//        }
//
//        Moto motoNova = new Moto(moto);
//        return motoRepository.save(motoNova);
        return new Moto();
    }

    public List<Moto> listar(){
        return motoRepository.findAll();
    }

    public Moto deletarMoto(Long id){
        Optional<Moto> moto = motoRepository.findById(id);
        if(moto.isEmpty()){
            throw new EntityNotFoundException("Moto não existe");
        }

        motoRepository.delete(moto.get());
        return moto.get();
    }
}
