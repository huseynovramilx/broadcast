package com.rooms.broadcast.Room;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Long roomId) {
        super("Could not find the room with id " + roomId);
    }
}
