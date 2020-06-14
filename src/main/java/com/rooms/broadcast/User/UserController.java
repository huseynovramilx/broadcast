package com.rooms.broadcast.User;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserInput userInput){
        return userService.addUser(userInput.getUserName());
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users/{id}/contacts")
    public Set<User> getContacts(@PathVariable Long id){
        return userService.getContacts(id);
    }

    @PostMapping("/users/{id}/contacts")
    public User addContact(@PathVariable Long id, @RequestBody ContactInput contactInput){
        return userService.addContact(id, contactInput.getContactId());
    }
}
