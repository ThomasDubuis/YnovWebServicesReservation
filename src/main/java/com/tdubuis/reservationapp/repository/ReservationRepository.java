package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Reservation;
import com.tdubuis.reservationapp.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findAllByExpiresAtIsBeforeAndStatus(Date expiresAt, Status status);
}
