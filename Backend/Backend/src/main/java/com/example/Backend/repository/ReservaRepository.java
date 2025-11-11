package com.example.Backend.repository;

import com.example.Backend.model.Reserva;
import com.example.Backend.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsBySalaAndDataAndHorario(Sala sala, LocalDate data, LocalTime horario);
    List<Reserva> findByData(LocalDate data);
}
