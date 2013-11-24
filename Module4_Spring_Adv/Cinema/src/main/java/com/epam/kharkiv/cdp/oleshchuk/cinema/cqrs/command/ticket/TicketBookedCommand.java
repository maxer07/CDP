package com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.ticket;

import com.epam.kharkiv.cdp.oleshchuk.cinema.cqrs.command.Command;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.TicketCategory;
import com.epam.kharkiv.cdp.oleshchuk.cinema.model.User;

import java.util.Date;
import java.util.List;

public class TicketBookedCommand extends TicketCommand{

    private User user;

    public TicketBookedCommand(final Long id, User user) {
        super(id);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
