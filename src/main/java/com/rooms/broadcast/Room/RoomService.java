package com.rooms.broadcast.Room;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoomService {
    Room addRoom(Long ownerId);
    Set<Room> getRoomsOfUser(Long ownerId);
    Room getRoom(Long roomId);
    Boolean roomOfOwner(Long roomId, String username);
}
