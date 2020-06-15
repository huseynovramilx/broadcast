package com.rooms.broadcast.User;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    User addUser(@RequestBody UserInput userInput){
        return userService.addUser(userInput.getUsername());
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/users/{id}/contacts")
    Set<User> getContacts(@PathVariable Long id){
        return userService.getContacts(id);
    }

    @GetMapping("/users/{id}/contacters")
    Set<User> getContacters(@PathVariable Long id){
        return userService.getContacters(id);
    }

    @PostMapping("/users/{id}/contacts")
    User addContact(@PathVariable Long id, @RequestBody ContactInput contactInput){
        return userService.addContact(id, contactInput.getContactId());
    }
}
