package com.tdubuis.reservationapp.controller;

import com.tdubuis.reservationapp.dto.request.ReservationRequest;
import com.tdubuis.reservationapp.dto.response.ReservationResponse;
import com.tdubuis.reservationapp.entity.Reservation;
import com.tdubuis.reservationapp.exception.NoResultException;
import com.tdubuis.reservationapp.service.ReservationService;
import com.tdubuis.reservationapp.utils.factory.ReservationFactory;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@PathVariable String movieUid, @Valid @RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationService.createReservation(movieUid, reservationRequest);
        return new ResponseEntity<>(ReservationFactory.toReservationResponse(reservation), HttpStatus.CREATED);
    }

    @PostMapping("/reservations/{uid}/confirm")
    public ResponseEntity<String> confirmReservation(@PathVariable String uid) {
        return new ResponseEntity<>(reservationService.confirmReservation(uid), HttpStatus.CREATED);
    }

    @GetMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationByMovieId(@PathVariable String movieUid) {
        List<Reservation> reservations = reservationService.getReservationByMovieId(movieUid);
        if (reservations.isEmpty()) {
            throw new NoResultException("No reservation found");
        }
        return ResponseEntity.ok(reservations.stream().map(ReservationFactory::toReservationResponse).toList());

    }

    @GetMapping("/reservations/{uid}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable String uid) {
        Reservation reservation = reservationService.getReservationById(uid);
        return ResponseEntity.ok(ReservationFactory.toReservationResponse(reservation));
    }
}
