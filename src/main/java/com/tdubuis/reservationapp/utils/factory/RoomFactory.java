package com.tdubuis.reservationapp.utils.factory;

import com.tdubuis.reservationapp.dto.request.RoomRequest;
import com.tdubuis.reservationapp.dto.response.RoomResponse;
import com.tdubuis.reservationapp.entity.Cinema;
import com.tdubuis.reservationapp.entity.Room;

public final class RoomFactory {

    private RoomFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Room toRoom(RoomRequest roomRequest, Cinema cinema) {
        return new Room()
                .setUid(roomRequest.getUid())
                .setName(roomRequest.getName())
                .setSeats(roomRequest.getSeats())
                .setCinema(cinema);
    }

    public static RoomResponse toRoomResponse(Room entity) {
        return new RoomResponse()
                .setUid(entity.getUid())
                .setName(entity.getName())
                .setSeats(entity.getSeats())
                .setCreatedAt(entity.getCreatedAt())
                .setUpdatedAt(entity.getUpdatedAt());
    }
}
