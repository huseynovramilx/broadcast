package com.rooms.broadcast.User;

import com.rooms.broadcast.User.Exceptions.UserAlreadyExistsException;
import com.rooms.broadcast.User.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
@Component
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException(username);
        }
        User user = new User(username);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId)  {
       Optional<User> optionalUser  = userRepository.findById(userId);
       if(optionalUser.isEmpty()){
           throw new UserNotFoundException(userId);
       }
       return optionalUser.get();
    }


    @Override
    public Set<User> getContacts(Long userId) {
        User user = getUser(userId);
        return user.getContacts();
    }

    @Override
    public Set<User> getContacters(Long userId) {
        User user = getUser(userId);
        return user.getContacters();
    }

    @Override
    public User addContact(Long userId, Long contactId) {
        User user = getUser(userId);
        User contact = getUser(contactId);
        user.addContact(contact);
        userRepository.save(user);
        return contact;
    }

    @Override
    public Boolean existsInUserContacts(Long ownerId,String username) {
        Optional<User> optionalContact = userRepository.findUserByUsername(username);
        if(optionalContact.isEmpty()){
            return false;
        }
        return userRepository.existsUserByIdAndContactsIsContaining(ownerId, optionalContact.get());
    }

    @Override
    public Boolean isContactersRoom(String username, Long roomId){
        return userRepository.isContactersRoom(username, roomId);
    }
}
