package com.example.Backend.controller;

import com.example.Backend.model.Sala;
import com.example.Backend.repository.SalaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = "*")
public class SalaController {
    private final SalaRepository repository;

    public SalaController(SalaRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Sala> listar() { return repository.findAll(); }

    @PostMapping
    public Sala criar(@RequestBody Sala sala) { return repository.save(sala); }
}
