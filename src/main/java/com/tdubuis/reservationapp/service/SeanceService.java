package com.tdubuis.reservationapp.service;

import com.tdubuis.reservationapp.dto.request.SeanceRequest;
import com.tdubuis.reservationapp.entity.Room;
import com.tdubuis.reservationapp.entity.Seance;
import com.tdubuis.reservationapp.exception.APINotAccessibleException;
import com.tdubuis.reservationapp.exception.ElementNotFoundException;
import com.tdubuis.reservationapp.exception.MovieNotExistException;
import com.tdubuis.reservationapp.repository.RoomRepository;
import com.tdubuis.reservationapp.repository.SeanceRepository;
import com.tdubuis.reservationapp.service.movie.MovieService;
import com.tdubuis.reservationapp.utils.factory.SeanceFactory;
import feign.FeignException;
import feign.RetryableException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SeanceService {
    private static final String UNABLE_TO_FIND_ROOM = "Unable to find Room [id = %s]";
    private static final String UNABLE_TO_FIND_SEANCE = "Unable to find Seance [id = %s]";
    private static final String ROOM_NOT_ASSIGN_TO_THE_CINEMA = "Room [id = %s] not assigned to the Cinema [id = %s]";
    private static final String SEANCE_NOT_ASSIGN_TO_THE_ROOM = "SEANCE [id = %s] not assigned to the Room [id = %s]";

    private final RoomRepository roomRepository;
    private final SeanceRepository seanceRepository;
    private final MovieService movieService;

    public List<Seance> getAllSeancesByRoom(String cinemaUid, String roomUid) {
        Room room = roomRepository.findById(roomUid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_ROOM, roomUid)));
        if (!room.getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, roomUid, cinemaUid));
        }
        return room.getSeances();
    }

    public Seance createSeance(String cinemaUid, String roomUid, SeanceRequest seanceRequest) {
        checkIfMovieExist(seanceRequest.getMovie());
        Room room = roomRepository.findById(roomUid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_ROOM, roomUid)));
        if (!room.getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, roomUid, cinemaUid));
        }
        Seance seance = SeanceFactory.toSeance(seanceRequest, room);
        return seanceRepository.save(seance);
    }

    public Seance updateSeance(String cinemaUid, String roomUid, String id, SeanceRequest updatedSeance) {
        if (!updatedSeance.getMovie().isBlank()) {
            checkIfMovieExist(updatedSeance.getMovie());
        }
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_SEANCE, id)));
        if (!seance.getRoom().getUid().equals(roomUid)) {
            throw new ElementNotFoundException(String.format(SEANCE_NOT_ASSIGN_TO_THE_ROOM, id, roomUid));
        }
        if (!seance.getRoom().getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, id, cinemaUid));
        }
        seance.updateIfNotNull(updatedSeance);
        return seanceRepository.save(seance);
    }

    public void deleteSeance(String cinemaUid, String roomUid, String id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_SEANCE, id)));
        if (!seance.getRoom().getUid().equals(roomUid)) {
            throw new ElementNotFoundException(String.format(SEANCE_NOT_ASSIGN_TO_THE_ROOM, id, roomUid));
        }
        if (!seance.getRoom().getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, id, cinemaUid));
        }
        seanceRepository.delete(seance);
    }

    private void checkIfMovieExist(String movieId) {
        try {
            movieService.getMovieById(movieId);
        } catch (FeignException.NotFound ex) {
            throw new MovieNotExistException("The movie[id = " + movieId + "] not exist");
        } catch (RetryableException ex) {
            throw new APINotAccessibleException("The movieAPI not accessible");
        }
    }
}
