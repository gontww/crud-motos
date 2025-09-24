package com.example.demo.controller;

import com.example.demo.dto.MotoDTO;
import com.example.demo.dto.MotoRequest;
import com.example.demo.service.MotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public List<MotoDTO> findAll() {
        return motoService.findAll();
    }

    @GetMapping("/disponiveis")
    public List<MotoDTO> findAllDisponiveis() {
        return motoService.findAllDisponiveis();
    }

    @GetMapping("/{id}")
    public MotoDTO findById(@PathVariable Long id) {
        return motoService.findById(id);
    }

    @PostMapping
    public MotoDTO save(@RequestBody MotoRequest moto) {
        return motoService.saveNova(moto);
    }

    @PutMapping("/{id}")
    public MotoDTO update(@PathVariable Long id, @RequestBody MotoRequest moto) {
        return motoService.update(id, moto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        motoService.deleteById(id);
    }
}