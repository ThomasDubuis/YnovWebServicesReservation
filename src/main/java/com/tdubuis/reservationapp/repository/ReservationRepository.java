package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
