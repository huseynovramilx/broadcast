package com.rooms.broadcast.User;

import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface UserService {
    User addUser(String userName);
    User getUser(Long userId);
    Set<User> getContacts(Long userId);
    Set<User> getContacters(Long userId);
    User addContact(Long userId, Long contactId);
    Boolean existsInUserContacts(Long ownerId,String username);
    Boolean isContactersRoom(String username, Long roomId);
}
