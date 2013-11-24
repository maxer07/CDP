package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event;


import java.io.Serializable;

public class UserCreatedEvent implements Event, Serializable {

    private Long identity;
    private String name;

    public UserCreatedEvent() {
    }

    public UserCreatedEvent(Long identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getIdentity() {
        return identity;
    }
}
