package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, String> {
    List<Seance> findAllByMovie(String movie);
}
