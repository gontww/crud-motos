package com.example.demo.controller;

import com.example.demo.model.Aluguel;
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
    public List<Aluguel> findAll() {
        return aluguelService.findAll();
    }

    @GetMapping("/{id}")
    public Aluguel findById(@PathVariable Long id) {
        return aluguelService.findById(id);
    }

    @PostMapping
    public Aluguel save(@RequestBody Aluguel aluguel) {
        return aluguelService.save(aluguel);
    }

    @PutMapping("/{id}")
    public Aluguel update(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        return aluguelService.update(id, aluguel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        aluguelService.deleteById(id);
    }
}
