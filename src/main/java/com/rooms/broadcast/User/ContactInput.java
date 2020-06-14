package com.rooms.broadcast.User;

import java.io.Serializable;

public class ContactInput implements Serializable {
    private Long contactId;

    public ContactInput() {
    }

    public ContactInput(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
