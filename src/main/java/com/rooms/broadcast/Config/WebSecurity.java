package com.rooms.broadcast.Config;

import com.rooms.broadcast.Room.RoomService;
import com.rooms.broadcast.User.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class WebSecurity {


    final UserService userService;
    final RoomService roomService;

    public WebSecurity(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }

    public boolean isOwner(Authentication authentication, Long ownerId){
        String ownerUsername = userService.getUser(ownerId).getUsername();
        return authentication.getName().equals(ownerUsername);
    }

    public boolean existsInOwnersContactsOrIsOwner(Authentication authentication, Long ownerId){
        if(isOwner(authentication, ownerId)){
            return true;
        }
        return userService.existsInUserContacts(ownerId, authentication.getName());
    }

    public boolean isContactersRoom(Authentication authentication, Long roomId){
        //this.existsInOwnersContacts(authentication, roomService.getRoom(roomId).getOwner().getId())
        if(isOwnersRoom(authentication, roomId)){

            return true;
        }
        return userService.isContactersRoom(authentication.getName(), roomId);
    }

    public boolean isOwnersRoom(Authentication authentication, Long roomId){
        return roomService.roomOfOwner(roomId, authentication.getName());
    }
}
