package com.tdubuis.reservationapp.service;

import com.tdubuis.reservationapp.dto.request.CinemaRequest;
import com.tdubuis.reservationapp.entity.Cinema;
import com.tdubuis.reservationapp.exception.ElementNotFoundException;
import com.tdubuis.reservationapp.exception.UidAlreadyExistOrNotConformException;
import com.tdubuis.reservationapp.repository.CinemaRepository;
import com.tdubuis.reservationapp.utils.factory.CinemaFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CinemaService {
    private static final String UNABLE_TO_FIND_CINEMA = "Unable to find Cinema [id = %s]";

    private final CinemaRepository cinemaRepository;

    public Page<Cinema> getAllCinema(Pageable pageable) {
        return cinemaRepository.findAll(pageable);
    }

    public Cinema getCinemaById(String uid) {
        return cinemaRepository.findById(uid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_CINEMA, uid)));
    }

    public Cinema createCinema(CinemaRequest cinemaRequest) {
        Cinema cinema = CinemaFactory.toCinema(cinemaRequest);
        if (cinema.getUid() != null && cinemaRepository.findById(cinema.getUid()).isPresent()) {
            throw new UidAlreadyExistOrNotConformException("Id[" + cinema.getUid() + "] already exist");
        }
        return cinemaRepository.save(cinema);
    }

    public Cinema updateCinema(String id, CinemaRequest updatedCinema) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_CINEMA, id)));
        cinema.updateIfNotNull(updatedCinema);
        return cinemaRepository.save(cinema);
    }

    public void deleteCinema(String id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_CINEMA, id)));
        cinemaRepository.delete(cinema);
    }

}
