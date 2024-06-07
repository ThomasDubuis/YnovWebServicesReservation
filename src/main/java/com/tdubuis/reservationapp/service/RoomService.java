package com.tdubuis.reservationapp.service;

import com.tdubuis.reservationapp.dto.request.RoomRequest;
import com.tdubuis.reservationapp.entity.Cinema;
import com.tdubuis.reservationapp.entity.Room;
import com.tdubuis.reservationapp.exception.ElementNotFoundException;
import com.tdubuis.reservationapp.exception.UidAlreadyExistOrNotConformException;
import com.tdubuis.reservationapp.repository.CinemaRepository;
import com.tdubuis.reservationapp.repository.RoomRepository;
import com.tdubuis.reservationapp.utils.factory.RoomFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoomService {
    private static final String UNABLE_TO_FIND_CINEMA = "Unable to find Cinema [id = %s]";
    private static final String UNABLE_TO_FIND_ROOM = "Unable to find Room [id = %s]";
    private static final String ROOM_NOT_ASSIGN_TO_THE_CINEMA = "Room [id = %s] not assigned to the Cinema [id = %s]";

    private final RoomRepository roomRepository;
    private final CinemaRepository cinemaRepository;

    public List<Room> getAllRoomsByCinema(String cinemaUid) {
        Cinema cinema = cinemaRepository.findById(cinemaUid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_CINEMA, cinemaUid)));
        return cinema.getRooms();
    }

    public Room getRoomById(String cinemaUid, String uid) {
        Room room = roomRepository.findById(uid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_ROOM, uid)));
        if (!room.getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, uid, cinemaUid));
        }
        return room;
    }

    public Room createRoom(String cinemaUid, RoomRequest roomRequest) {
        Cinema cinema = cinemaRepository.findById(cinemaUid).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_CINEMA, cinemaUid)));
        Room room = RoomFactory.toRoom(roomRequest, cinema);
        if (room.getUid() != null && roomRepository.findById(room.getUid()).isPresent()) {
            throw new UidAlreadyExistOrNotConformException("Id[" + room.getUid() + "] already exist");
        }
        return roomRepository.save(room);
    }

    public Room updateRoom(String cinemaUid, String id, RoomRequest updatedRoom) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_ROOM, id)));
        if (!room.getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, id, cinemaUid));
        }
        room.updateIfNotNull(updatedRoom);
        return roomRepository.save(room);
    }

    public void deleteRoom(String cinemaUid, String id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format(UNABLE_TO_FIND_ROOM, id)));
        if (!room.getCinema().getUid().equals(cinemaUid)) {
            throw new ElementNotFoundException(String.format(ROOM_NOT_ASSIGN_TO_THE_CINEMA, id, cinemaUid));
        }
        roomRepository.delete(room);
    }
}
