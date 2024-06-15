package com.tdubuis.reservationapp.utils.factory;

import com.tdubuis.reservationapp.dto.request.ReservationRequest;
import com.tdubuis.reservationapp.dto.response.ReservationResponse;
import com.tdubuis.reservationapp.entity.Reservation;
import com.tdubuis.reservationapp.entity.Seance;
import com.tdubuis.reservationapp.entity.Status;

import java.util.Date;

public final class ReservationFactory {

    private ReservationFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Reservation toReservation(ReservationRequest reservationRequest, Seance seance, Integer rank, Date expiresAt) {
        return new Reservation()
                .setSeance(seance)
                .setRank(rank)
                .setExpiresAt(expiresAt)
                .setSeats(reservationRequest.getNbSeats())
                .setStatus(Status.open);
    }

    public static ReservationResponse toReservationResponse(Reservation entity) {
        return new ReservationResponse()
                .setUid(entity.getUid())
                .setRank(entity.getRank())
                .setStatus(entity.getStatus())
                .setExpiresAt(entity.getExpiresAt())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }
}
