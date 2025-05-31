package com.example.demo.controller;

import com.example.demo.model.Moto;
import com.example.demo.service.MotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("moto")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping("/criar")
        public Moto criarMoto(@RequestBody Moto moto) {
        return motoService.criarMoto(moto);
    }

    @GetMapping("/listar")
    public List<Moto> listar() {
        return motoService.listar();
    }

    @DeleteMapping("/excluir/{id}")
    public Moto excluir(@PathVariable("id") Long id) {
        return motoService.deletarMoto(id);
    }
}
