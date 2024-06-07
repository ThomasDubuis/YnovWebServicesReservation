package com.tdubuis.reservationapp.utils.factory;

import com.tdubuis.reservationapp.dto.request.CinemaRequest;
import com.tdubuis.reservationapp.dto.response.CinemaResponse;
import com.tdubuis.reservationapp.entity.Cinema;
import org.springframework.data.domain.Page;

public final class CinemaFactory {

    private CinemaFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Cinema toCinema(CinemaRequest cinemaRequest) {
        return new Cinema()
                .setUid(cinemaRequest.getUid())
                .setName(cinemaRequest.getName());
    }

    public static CinemaResponse toCinemaResponse(Cinema entity) {
        return new CinemaResponse()
                .setUid(entity.getUid())
                .setName(entity.getName())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }

    public static Page<CinemaResponse> toPageCinemaResponse(Page<Cinema> cinemaPage) {
        return cinemaPage.map(CinemaFactory::toCinemaResponse);
    }
}
