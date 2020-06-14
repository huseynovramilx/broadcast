package com.rooms.broadcast.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;
@Component
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(String userName) {
        User user = new User(userName);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId)  {
       Optional<User> optionalUser  = userRepository.findById(userId);
       if(optionalUser.isEmpty()){

       }
       return optionalUser.get();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isEmpty()){

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
    public void removeContact(Long userId, Long contactId) {
        User user = getUser(userId);
        User contact = getUser(contactId);
        user.removeContact(contact);
        userRepository.save(user);
    }

    @Override
    public Boolean isContactersRoom(String username, Long roomId){
        return userRepository.isContactersRoom(username, roomId);
    }
}
