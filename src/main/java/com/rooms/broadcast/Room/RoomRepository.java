package com.rooms.broadcast.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Boolean existsRoomByIdAndOwner_Username(Long roomId, String ownerUsername);
}
