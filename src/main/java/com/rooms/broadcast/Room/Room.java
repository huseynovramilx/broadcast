package com.rooms.broadcast.Room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rooms.broadcast.User.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User owner;

    public Room() {
    }

    public Room(User owner) {
        owner.addRoom(this);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }
}
