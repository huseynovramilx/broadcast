package com.rooms.broadcast.User;

import java.io.Serializable;

public class UserInput implements Serializable {
    private String username;

    public UserInput() {
    }

    public UserInput(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
}
