package com.tdubuis.reservationapp.controller;

import com.tdubuis.reservationapp.dto.request.CinemaRequest;
import com.tdubuis.reservationapp.dto.request.RoomRequest;
import com.tdubuis.reservationapp.dto.request.SeanceRequest;
import com.tdubuis.reservationapp.dto.response.CinemaResponse;
import com.tdubuis.reservationapp.dto.response.RoomResponse;
import com.tdubuis.reservationapp.dto.response.SeanceResponse;
import com.tdubuis.reservationapp.entity.Cinema;
import com.tdubuis.reservationapp.entity.Room;
import com.tdubuis.reservationapp.entity.Seance;
import com.tdubuis.reservationapp.exception.ElementNotFoundException;
import com.tdubuis.reservationapp.exception.NoResultException;
import com.tdubuis.reservationapp.service.CinemaService;
import com.tdubuis.reservationapp.service.RoomService;
import com.tdubuis.reservationapp.service.SeanceService;
import com.tdubuis.reservationapp.utils.factory.CinemaFactory;
import com.tdubuis.reservationapp.utils.factory.RoomFactory;
import com.tdubuis.reservationapp.utils.factory.SeanceFactory;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
@Slf4j
@AllArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;
    private final RoomService roomService;
    private final SeanceService seanceService;

    @GetMapping
    public ResponseEntity<Page<CinemaResponse>> getAllCinema(Pageable pageable) {
        Page<Cinema> cinemas = cinemaService.getAllCinema(pageable);
        if (cinemas.getTotalElements() == 0) {
            throw new NoResultException("No cinema found");
        }
        if (cinemas.getNumberOfElements() == 0) {
            throw new ElementNotFoundException("Page not exist");
        }
        return ResponseEntity.ok(CinemaFactory.toPageCinemaResponse(cinemas));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<CinemaResponse> getCinemaById(@PathVariable String uid) {
        Cinema cinema = cinemaService.getCinemaById(uid);
        return ResponseEntity.ok(CinemaFactory.toCinemaResponse(cinema));
    }

    @PostMapping
    public ResponseEntity<CinemaResponse> createCinema(@Valid @RequestBody CinemaRequest cinemaRequest) {
        Cinema cinemaCreated = cinemaService.createCinema(cinemaRequest);
        return new ResponseEntity<>(CinemaFactory.toCinemaResponse(cinemaCreated), HttpStatus.CREATED);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<CinemaResponse> updateCinema(@Valid @RequestBody CinemaRequest cinemaRequest,
                                                       @PathVariable String uid) {
        Cinema updatedCinema = cinemaService.updateCinema(uid, cinemaRequest);
        return ResponseEntity.ok(CinemaFactory.toCinemaResponse(updatedCinema));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteCinema(@PathVariable String uid) {
        cinemaService.deleteCinema(uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{cinemaUid}/rooms")
    public ResponseEntity<List<RoomResponse>> getAllRoomsByCinema(@PathVariable String cinemaUid) {
        List<Room> rooms = roomService.getAllRoomsByCinema(cinemaUid);
        if (rooms.isEmpty()) {
            throw new NoResultException("No rooms found");
        }
        return ResponseEntity.ok(rooms.stream().map(RoomFactory::toRoomResponse).toList());
    }

    @GetMapping("/{cinemaUid}/rooms/{uid}")
    public ResponseEntity<RoomResponse> getRoomsById(@PathVariable String cinemaUid, @PathVariable String uid) {
        Room room = roomService.getRoomById(cinemaUid, uid);
        return ResponseEntity.ok(RoomFactory.toRoomResponse(room));
    }

    @PostMapping("/{cinemaUid}/rooms")
    public ResponseEntity<RoomResponse> createRooms(@PathVariable String cinemaUid, @Valid @RequestBody RoomRequest roomRequest) {
        Room roomCreated = roomService.createRoom(cinemaUid, roomRequest);
        return new ResponseEntity<>(RoomFactory.toRoomResponse(roomCreated), HttpStatus.CREATED);
    }

    @PutMapping("/{cinemaUid}/rooms/{uid}")
    public ResponseEntity<RoomResponse> updateRooms(@PathVariable String cinemaUid, @PathVariable String uid, @Valid @RequestBody RoomRequest roomRequest) {
        Room updatedRoom = roomService.updateRoom(cinemaUid, uid, roomRequest);
        return ResponseEntity.ok(RoomFactory.toRoomResponse(updatedRoom));
    }

    @DeleteMapping("/{cinemaUid}/rooms/{uid}")
    public ResponseEntity<String> deleteRooms(@PathVariable String cinemaUid, @PathVariable String uid) {
        roomService.deleteRoom(cinemaUid, uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{cinemaUid}/rooms/{roomUid}/sceances")
    public ResponseEntity<List<SeanceResponse>> getAllSeances(@PathVariable String cinemaUid, @PathVariable String roomUid) {
        List<Seance> seances = seanceService.getAllSeancesByRoom(cinemaUid, roomUid);
        if (seances.isEmpty()) {
            throw new NoResultException("No Seances found");
        }
        return ResponseEntity.ok(seances.stream().map(SeanceFactory::toSeanceResponse).toList());
    }

    @PostMapping("/{cinemaUid}/rooms/{roomUid}/sceances")
    public ResponseEntity<SeanceResponse> createSeance(@PathVariable String cinemaUid, @PathVariable String roomUid, @Valid @RequestBody SeanceRequest seanceRequest) {
        Seance seanceCreated = seanceService.createSeance(cinemaUid, roomUid, seanceRequest);
        return new ResponseEntity<>(SeanceFactory.toSeanceResponse(seanceCreated), HttpStatus.CREATED);
    }

    @PutMapping("/{cinemaUid}/rooms/{roomUid}/sceances/{uid}")
    public ResponseEntity<SeanceResponse> updateSeance(@PathVariable String cinemaUid, @PathVariable String roomUid, @PathVariable String uid, @Valid @RequestBody SeanceRequest seanceRequest) {
        Seance updatedSeance = seanceService.updateSeance(cinemaUid, roomUid, uid, seanceRequest);
        return ResponseEntity.ok(SeanceFactory.toSeanceResponse(updatedSeance));
    }

    @DeleteMapping("/{cinemaUid}/rooms/{roomUid}/sceances/{uid}")
    public ResponseEntity<String> deleteSeance(@PathVariable String cinemaUid, @PathVariable String roomUid, @PathVariable String uid) {
        seanceService.deleteSeance(cinemaUid, roomUid, uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
