package com.example.demo.controller;

import com.example.demo.model.Locatario;
import com.example.demo.service.LocatarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/locatarios")
public class LocatarioController {

    private final LocatarioService locatarioService;

    public LocatarioController(LocatarioService locatarioService) {
        this.locatarioService = locatarioService;
    }

    @GetMapping
    public List<Locatario> findAll() {
        return locatarioService.findAll();
    }

    @GetMapping("/{id}")
    public Locatario findById(@PathVariable Long id) {
        return locatarioService.findById(id);
    }

    @PostMapping
    public Locatario save(@RequestBody Locatario locatario) {
        return locatarioService.save(locatario);
    }

    @PutMapping("/{id}")
    public Locatario update(@PathVariable Long id, @RequestBody Locatario locatario) {
        return locatarioService.update(id, locatario);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        locatarioService.deleteById(id);
    }
}
