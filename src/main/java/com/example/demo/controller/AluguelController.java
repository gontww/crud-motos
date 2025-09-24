package com.example.demo.controller;

import com.example.demo.dto.AluguelDTO;
import com.example.demo.dto.AluguelRequest;
import com.example.demo.service.AluguelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public List<AluguelDTO> findAll() {
        return aluguelService.findAll();
    }

    @GetMapping("/{id}")
    public AluguelDTO findById(@PathVariable Long id) {
        return aluguelService.findById(id);
    }

    @PostMapping
    public AluguelDTO save(@RequestBody AluguelRequest aluguel) {
        return aluguelService.save(aluguel);
    }

    @PutMapping("/{id}")
    public AluguelDTO update(@PathVariable Long id, @RequestBody AluguelRequest aluguel) {
        return aluguelService.update(id, aluguel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        aluguelService.deleteById(id);
    }

    @GetMapping("/{id}/finalizar")
    public AluguelDTO finalizar(@PathVariable Long id) {
        return aluguelService.finalizar(id);
    }
}
