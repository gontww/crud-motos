package com.example.demo.controller;

import com.example.demo.model.Moto;
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
    public List<Moto> findAll() {
        return motoService.findAll();
    }

    @GetMapping("/disponiveis")
    public List<Moto> findAllDisponiveis() {
        return motoService.findAllDisponiveis();
    }

    @GetMapping("/{id}")
    public Moto findById(@PathVariable Long id) {
        return motoService.findById(id);
    }

    @PostMapping
    public Moto save(@RequestBody Moto moto) {
        return motoService.save(moto);
    }

    @PutMapping("/{id}")
    public Moto update(@PathVariable Long id, @RequestBody Moto moto) {
        return motoService.update(id, moto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        motoService.deleteById(id);
    }
}