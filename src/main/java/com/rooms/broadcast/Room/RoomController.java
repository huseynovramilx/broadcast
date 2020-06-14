package com.rooms.broadcast.Room;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoomController {

    private  final  RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(value = "/users/{id}/rooms")
    public Set<Room> getRoomsOfUser(@PathVariable Long id){
        return roomService.getRoomsOfUser(id);
    }

    @PostMapping(value = "/users/{id}/rooms")
    public Room addRoom(@PathVariable Long id){
       return roomService.addRoom(id);
    }

    @MessageMapping(value = "/rooms/{id}")
    public Broadcast sendData(@DestinationVariable Long id, Broadcast broadcast){
        return broadcast;
    }
}
