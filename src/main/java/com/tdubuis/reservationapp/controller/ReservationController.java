package com.tdubuis.reservationapp.controller;

import com.tdubuis.reservationapp.dto.request.ReservationRequest;
import com.tdubuis.reservationapp.dto.response.ReservationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ReservationController {

    @PostMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest reservationRequest, @PathVariable String movieUid) {
        return null;
    }

    @PostMapping("/reservations/{uid}/confirm")
    public ResponseEntity<String> confirmReservation(@PathVariable String uid) {
        return null;
    }

    @GetMapping("/movie/{movieUid}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationByMovieId(@PathVariable String movieUid) {
        return null;
    }

    @GetMapping("/reservations/{uid}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable String uid) {
        return null;
    }
}
