package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, String> {
}
