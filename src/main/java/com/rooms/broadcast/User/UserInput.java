package com.rooms.broadcast.User;

import java.io.Serializable;

public class UserInput implements Serializable {
    private String userName;

    public UserInput() {
    }

    public UserInput(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
