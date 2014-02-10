package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.event.Event;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

public class TicketBookedEvent implements Event {

    private Long identity;
    private User user;

    public TicketBookedEvent(Long identity, User user) {
        this.identity = identity;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getIdentity() {
        return identity;
    }
    
}
