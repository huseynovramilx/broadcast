package com.rooms.broadcast.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rooms.broadcast.Room.Room;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;


    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "contacters_contacts",
            joinColumns = {
                    @JoinColumn(name = "contacter_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "contact_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<User> contacts = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(mappedBy = "contacts",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> contacters = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    public void addContact(User contact){
        this.contacts.add(contact);
        contact.contacters.add(this);
    }

    public void removeContact(User contact){
        this.contacts.remove(contact);
        contact.contacters.remove(this);
    }

    public void addRoom(Room room){
        this.rooms.add(room);
        room.setOwner(this);
    }
    public void removeRoom(Room room){
        room.setOwner(null);
        this.rooms.remove(room);
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<User> getContacts() {
        return contacts;
    }

    public void setContacts(Set<User> contacts) {
        this.contacts = contacts;
    }

    public Set<User> getContacters() {
        return contacters;
    }

    public void setContacters(Set<User> contacters) {
        this.contacters = contacters;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }
}
