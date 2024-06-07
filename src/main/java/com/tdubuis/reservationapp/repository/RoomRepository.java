package com.tdubuis.reservationapp.repository;

import com.tdubuis.reservationapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
