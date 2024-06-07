package com.tdubuis.reservationapp.utils.factory;

import com.tdubuis.reservationapp.dto.request.SeanceRequest;
import com.tdubuis.reservationapp.dto.response.SeanceResponse;
import com.tdubuis.reservationapp.entity.Room;
import com.tdubuis.reservationapp.entity.Seance;

public final class SeanceFactory {

    private SeanceFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Seance toSeance(SeanceRequest seanceRequest, Room room) {
        return new Seance()
                .setMovie(seanceRequest.getMovie())
                .setDate(seanceRequest.getDate())
                .setRoom(room);
    }

    public static SeanceResponse toSeanceResponse(Seance entity) {
        return new SeanceResponse()
                .setUid(entity.getUid())
                .setMovie(entity.getMovie())
                .setDate(entity.getDate())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }
}
