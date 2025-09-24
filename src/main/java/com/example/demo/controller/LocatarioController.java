package com.example.demo.controller;

import com.example.demo.dto.LocatarioDTO;
import com.example.demo.dto.LocatarioRequest;
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
    public List<LocatarioDTO> findAll() {
        return locatarioService.findAll();
    }

    @GetMapping("/{id}")
    public LocatarioDTO findById(@PathVariable Long id) {
        return locatarioService.findById(id);
    }

    @PostMapping
    public LocatarioDTO save(@RequestBody LocatarioRequest locatario) {
        return locatarioService.save(locatario);
    }

    @PutMapping("/{id}")
    public LocatarioDTO update(@PathVariable Long id, @RequestBody LocatarioRequest locatario) {
        return locatarioService.update(id, locatario);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        locatarioService.deleteById(id);
    }
}
