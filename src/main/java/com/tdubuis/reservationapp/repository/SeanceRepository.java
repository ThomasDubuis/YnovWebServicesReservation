package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance, String> {
}
