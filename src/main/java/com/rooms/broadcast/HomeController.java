package com.rooms.broadcast;

import com.rooms.broadcast.Room.RoomService;
import com.rooms.broadcast.User.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    final RoomService roomService;

    public HomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/rooms/{roomId}")
    public String Index(@PathVariable("roomId") Long roomId, Model model, Authentication authentication){
        if(roomService.roomOfOwner(roomId, authentication.getName())){
            model.addAttribute("owner", true);
        }
        else{
            model.addAttribute("owner", false);
        }
        model.addAttribute("roomId", roomId);
        return "index";
    }
}
