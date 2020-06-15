package com.rooms.broadcast.Room;

import com.rooms.broadcast.User.User;
import com.rooms.broadcast.User.UserRepository;
import com.rooms.broadcast.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Component
public class RoomServiceImpl implements RoomService {

    final UserService userService;
    final RoomRepository roomRepository;

    public RoomServiceImpl(UserService userService, RoomRepository roomRepository) {
        this.userService = userService;
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(Long ownerId) {
        User user = userService.getUser(ownerId);
        Room room = new Room(user);
        return roomRepository.save(room);

    }

    @Override
    public Set<Room> getRoomsOfUser(Long ownerId) {
        User user = userService.getUser(ownerId);
        return user.getRooms();
    }

    @Override
    public Room getRoom(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isEmpty()){
            throw new RoomNotFoundException(roomId);
        }
        return optionalRoom.get();
    }

    @Override
    public Boolean roomOfOwner(Long roomId, String username) {
        return roomRepository.existsRoomByIdAndOwner_Username(roomId, username);
    }
}
