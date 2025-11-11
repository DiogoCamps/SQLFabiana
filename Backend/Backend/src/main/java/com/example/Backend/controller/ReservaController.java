package com.example.Backend.controller;

import com.example.Backend.model.Reserva;
import com.example.Backend.repository.ReservaRepository;
import com.example.Backend.repository.SalaRepository;
import com.example.Backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaRepository reservaRepo;
    private final UsuarioRepository usuarioRepo;
    private final SalaRepository salaRepo;

    public ReservaController(ReservaRepository reservaRepo, UsuarioRepository usuarioRepo, SalaRepository salaRepo) {
        this.reservaRepo = reservaRepo;
        this.usuarioRepo = usuarioRepo;
        this.salaRepo = salaRepo;
    }

    @GetMapping
    public List<Reserva> listar() { return reservaRepo.findAll(); }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Reserva reserva) {
        // Vincula usuário e sala
        reserva.setUsuario(usuarioRepo.findById(reserva.getUsuario().getId()).orElse(null));
        reserva.setSala(salaRepo.findById(reserva.getSala().getId()).orElse(null));

        // Checa conflito de horário
        if (reservaRepo.existsBySalaAndDataAndHorario(reserva.getSala(), reserva.getData(), reserva.getHorario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Essa sala já está reservada nesse horário!");
        }

        return ResponseEntity.ok(reservaRepo.save(reserva));
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id) { reservaRepo.deleteById(id); }
}
